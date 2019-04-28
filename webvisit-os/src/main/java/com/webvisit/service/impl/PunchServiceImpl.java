package com.webvisit.service.impl;

import com.webvisit.common.constant.LocalConstant;
import com.webvisit.common.enums.PunchInEnum;
import com.webvisit.dao.AttencePunchDetailExtMapper;
import com.webvisit.dao.common.AttenceRegulationMapper;
import com.webvisit.dao.common.CompanyDeptMapper;
import com.webvisit.dao.common.UserMapper;
import com.webvisit.model.po.AttencePunchDetail;
import com.webvisit.model.po.AttenceRegulation;
import com.webvisit.model.po.CompanyDept;
import com.webvisit.model.po.User;
import com.webvisit.model.vo.PunchVO;
import com.webvisit.service.PunchService;
import com.webvisit.utils.TimeUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/28
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class PunchServiceImpl implements PunchService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CompanyDeptMapper companyDeptMapper;
    @Resource
    private AttenceRegulationMapper attenceRegulationMapper;
    @Resource
    private AttencePunchDetailExtMapper attencePunchDetailExtMapper;

    @Override
    public Boolean punch(PunchVO punchVO) {
        //查询用户对应的考勤规则
        User user = userMapper.selectByPrimaryKey(punchVO.getEmpId());
        CompanyDept dept = companyDeptMapper.selectByPrimaryKey(user.getDeptId());
        AttenceRegulation attenceRegulation = attenceRegulationMapper.selectByPrimaryKey(dept.getAttenceRegulationId());
        //转化为对应的时间格式
        Date punchTime = TimeUtil.createNowTime();
        SimpleDateFormat dateFormatWithHHMMSS = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormatWithMMDD = new SimpleDateFormat("MM-dd");
        String formatPunchTime = dateFormatWithHHMMSS.format(punchTime);
        Date punchInEndTime = attenceRegulation.getPunchInEnd();
        String formatPunchInEndTime = dateFormatWithHHMMSS.format(punchInEndTime);
        //查询今天是否已有打卡
        String key = LocalConstant.PUNCH_UUID_KEY + dateFormatWithMMDD.format(punchTime) + punchVO.getEmpId();
        AttencePunchDetail punchDetail = (AttencePunchDetail) redisTemplate.opsForValue().get(key);
        //正常签到
        if (TimeUtil.compareHHMMSSTime(formatPunchTime, formatPunchInEndTime) < 1) {
            //如果没有打卡过，则直接进行打卡，否则即是已经签到过
            if (null == punchDetail) {
                AttencePunchDetail attencePunchDetail = generatePunchDetail(punchVO,PunchInEnum.NORMAL.getCode(),punchTime);
                if (attencePunchDetailExtMapper.insertPunchDetailSelectiveReturnId(attencePunchDetail) == 1) {
                    redisTemplate.opsForValue().set(key, attencePunchDetail);
                }
            }
        } else {
            //迟到
            String formatAllowLate = dateFormatWithHHMMSS.format(punchInEndTime.getTime() + TimeUtil.getMSOfTime(attenceRegulation.getAllowLate()));
            if (TimeUtil.compareHHMMSSTime(formatPunchTime, formatAllowLate) < 1) {
                AttencePunchDetail attencePunchDetail = generatePunchDetail(punchVO,PunchInEnum.LATE.getCode(),punchTime);
                if (attencePunchDetailExtMapper.insertPunchDetailSelectiveReturnId(attencePunchDetail) == 1) {
                    redisTemplate.opsForValue().set(key,attencePunchDetail);
                }
            }
            Date punchOutStartTime = attenceRegulation.getPunchOutStart();
            String formatPunchOutStartTime = dateFormatWithHHMMSS.format(punchOutStartTime);
        }
        return true;
    }

    private AttencePunchDetail generatePunchDetail(PunchVO punchVO, Integer punchStatus, Date punchTime) {
        AttencePunchDetail attencePunchDetail = new AttencePunchDetail();
        attencePunchDetail.setEmpId(punchVO.getEmpId());
        attencePunchDetail.setPunchTime(punchTime);
        attencePunchDetail.setPunchInTime(punchTime);
        attencePunchDetail.setPunchInStatus(punchStatus);
        attencePunchDetail.setPunchInLocationLat(punchVO.getPunchLocationLat());
        attencePunchDetail.setPunchInLocationLon(punchVO.getPunchLocationLng());
        return attencePunchDetail;
    }
}

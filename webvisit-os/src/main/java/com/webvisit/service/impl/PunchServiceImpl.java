package com.webvisit.service.impl;

import com.webvisit.common.constant.LocalConstant;
import com.webvisit.common.enums.PunchInEnum;
import com.webvisit.common.enums.PunchOutEnum;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.dao.AttencePunchDetailExtMapper;
import com.webvisit.dao.common.AttencePunchDetailMapper;
import com.webvisit.dao.common.AttenceRegulationMapper;
import com.webvisit.dao.common.CompanyDeptMapper;
import com.webvisit.dao.common.UserMapper;
import com.webvisit.model.po.AttencePunchDetail;
import com.webvisit.model.po.AttenceRegulation;
import com.webvisit.model.po.CompanyDept;
import com.webvisit.model.po.User;
import com.webvisit.model.vo.PunchDetailVO;
import com.webvisit.model.vo.PunchVO;
import com.webvisit.service.PunchService;
import com.webvisit.utils.LocationUtils;
import com.webvisit.utils.TimeUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private AttencePunchDetailMapper attencePunchDetailMapper;

    @Override
    @Async
    public void punch(PunchVO punchVO) {
        //查询用户对应的考勤规则
        User user = userMapper.selectByPrimaryKey(punchVO.getEmpId());
        CompanyDept dept = companyDeptMapper.selectByPrimaryKey(user.getDeptId());
        AttenceRegulation attenceRegulation = attenceRegulationMapper.selectByPrimaryKey(dept.getAttenceRegulationId());
        //检查打卡地点
        if (attenceRegulation.getAllowLocationOffset() != null) {
            double locationOffset = LocationUtils.getDistanceMeter(punchVO.getPunchLocationLat(),punchVO.getPunchLocationLng(),attenceRegulation.getCheckLocationLat(),attenceRegulation.getCheckLocationLon());
            if (locationOffset > attenceRegulation.getAllowLocationOffset()) {
                throw new BusinessException("没有在允许的范围内打卡");
            }
        }
        //转化为对应的时间格式
        Date punchTime = TimeUtil.createNowTime();
        SimpleDateFormat dateFormatWithMMDD = new SimpleDateFormat("MM-dd");
        String formatPunchDate = dateFormatWithMMDD.format(punchTime);
        //key
        String lateKey = LocalConstant.PUNCH_LATE_KEY + formatPunchDate + punchVO.getEmpId();
        String punchInKey = LocalConstant.PUNCH_IN_KEY + formatPunchDate + dept.getId();
        String punchOutKey = LocalConstant.PUNCH_OUT_KEY + formatPunchDate + dept.getId();
        punchIn(punchVO, attenceRegulation, punchInKey, lateKey);
        punchOut(punchVO, attenceRegulation, punchOutKey, lateKey);
    }

    @Override
    @Async
    public void punchIn(PunchVO punchVO, AttenceRegulation regulation, String punchInKey, String lateKey) {
        Boolean hasPunchIn = redisTemplate.opsForSet().isMember(punchInKey, punchVO.getEmpId());
        //如果没有签到过则进行签到，否则不签到
        Date punchTime = TimeUtil.createNowTime();
        if (hasPunchIn == null || !hasPunchIn) {
            //正常签到
            if (TimeUtil.compareTime(punchTime, regulation.getPunchInEnd()) < 1) {
                //在开始签到后结束签到前签到，加入弹性时间
                if (TimeUtil.compareTime(punchTime, regulation.getPunchInStart()) > -1) {
                    long late = TimeUtil.getDiffMilliSecondOfTwoTime(punchTime, regulation.getPunchInStart());
                    redisTemplate.opsForValue().set(lateKey, late, 1, TimeUnit.DAYS);
                }
                //进行签到
                AttencePunchDetail punchDetail = generatePunchInDetail(punchVO, PunchInEnum.NORMAL.getCode(), punchTime);
                attencePunchDetailExtMapper.insertPunchDetailSelectiveReturnId(punchDetail);
                redisTemplate.opsForSet().add(punchInKey, punchVO.getEmpId());
            } else {
                //在允许迟到范围内，迟到打卡
                Date allowLate = new Date(regulation.getPunchInEnd().getTime() + TimeUtil.getMilliSecondOfTime(regulation.getAllowLate()));
                if (TimeUtil.compareTime(punchTime, allowLate) < 1) {
                    AttencePunchDetail punchDetail = generatePunchInDetail(punchVO, PunchInEnum.LATE.getCode(), punchTime);
                    attencePunchDetailExtMapper.insertPunchDetailSelectiveReturnId(punchDetail);
                    redisTemplate.opsForSet().add(punchInKey, punchVO.getEmpId());
                }
            }
        }
    }

    @Override
    @Async
    public void punchOut(PunchVO punchVO, AttenceRegulation attenceRegulation, String punchOutKey, String lateKey) {
        Date punchTime = TimeUtil.createNowTime();
        Long late = (Long) redisTemplate.opsForValue().get(lateKey);
        Long early = attenceRegulation.getAllowLeaveEarly() == null ? 0 : TimeUtil.getMilliSecondOfTime(attenceRegulation.getAllowLeaveEarly());
        Date allowOut = late == null ? attenceRegulation.getPunchOutStart() : new Date(attenceRegulation.getPunchOutStart().getTime() + late);
        Date allowEarly = new Date(allowOut.getTime() - early);
        //如果在正常签退前允许早退后签退则记录早退
        if (TimeUtil.compareTime(punchTime, allowEarly) > -1) {
            AttencePunchDetail attencePunchDetail;
            if (TimeUtil.compareTime(punchTime, allowOut) < 1) {
                attencePunchDetail = generatePunchOutDetail(punchVO, PunchOutEnum.EARLY.getCode(), punchTime);
            } else {
                attencePunchDetail = generatePunchOutDetail(punchVO, PunchOutEnum.NORMAL.getCode(), punchTime);
            }
            List<PunchDetailVO> punchDetailList = attencePunchDetailExtMapper.selectByCondition(PunchDetailVO.builder().empId(punchVO.getEmpId()).punchTime(punchTime).build());
            if (!punchDetailList.isEmpty()) {
                attencePunchDetail.setId(punchDetailList.get(0).getId());
                attencePunchDetailMapper.updateByPrimaryKeySelective(attencePunchDetail);
            } else {
                attencePunchDetailMapper.insertSelective(attencePunchDetail);
            }
            redisTemplate.opsForSet().add(punchOutKey, punchVO.getEmpId());
        }
    }

    private AttencePunchDetail generatePunchOutDetail(PunchVO punchVO, Integer punchStatus, Date punchTime) {
        AttencePunchDetail attencePunchDetail = new AttencePunchDetail();
        attencePunchDetail.setEmpId(punchVO.getEmpId());
        attencePunchDetail.setPunchTime(punchTime);
        attencePunchDetail.setPunchOutTime(punchTime);
        attencePunchDetail.setPunchOutStatus(punchStatus);
        attencePunchDetail.setPunchOutLocationLat(punchVO.getPunchLocationLat());
        attencePunchDetail.setPunchOutLocationLon(punchVO.getPunchLocationLng());
        return attencePunchDetail;
    }

    private AttencePunchDetail generatePunchInDetail(PunchVO punchVO, Integer punchStatus, Date punchTime) {
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

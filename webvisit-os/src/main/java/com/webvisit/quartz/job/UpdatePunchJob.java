package com.webvisit.quartz.job;

import com.webvisit.common.constant.LocalConstant;
import com.webvisit.common.enums.PunchInEnum;
import com.webvisit.common.enums.PunchOutEnum;
import com.webvisit.dao.AttencePunchDetailExtMapper;
import com.webvisit.dao.CompanyDeptExtMapper;
import com.webvisit.dao.UserExtMapper;
import com.webvisit.dao.common.AttencePunchDetailMapper;
import com.webvisit.model.po.AttencePunchDetail;
import com.webvisit.model.po.CompanyDept;
import com.webvisit.model.po.User;
import com.webvisit.model.vo.PunchDetailVO;
import com.webvisit.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/28
 */

@Component("updatePunchJob")
@Transactional(rollbackFor = Exception.class)
public class UpdatePunchJob {

    @Resource
    private RedisTemplate<String, Long> redisTemplate;
    @Resource
    private CompanyDeptExtMapper companyDeptExtMapper;
    @Resource
    private UserExtMapper userExtMapper;
    @Resource
    private AttencePunchDetailMapper attencePunchDetailMapper;
    @Resource
    private AttencePunchDetailExtMapper attencePunchDetailExtMapper;

    private final static Logger logger = LoggerFactory.getLogger(UpdatePunchJob.class);

    public void execute() {
        List<CompanyDept> companyDeptList = companyDeptExtMapper.queryDeptByCondition(new CompanyDept());
        Date punchTime = TimeUtil.getLastDay();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        String formatPunchTime = format.format(punchTime);
        for (CompanyDept dept : companyDeptList) {
            Long deptId = dept.getId();
            String punchInKey = LocalConstant.PUNCH_IN_KEY + formatPunchTime + deptId;
            String punchOutKey = LocalConstant.PUNCH_OUT_KEY + formatPunchTime +deptId;
            Set<Long> punchedInList = redisTemplate.opsForSet().members(punchInKey);
            Set<Long> punchedOutList = redisTemplate.opsForSet().members(punchOutKey);
            User query = new User();
            query.setDeptId(deptId);
            List<User> userList = userExtMapper.selectByCondition(query);
            List<Long> punchInEmpIdList = new ArrayList<>();
            List<Long> punchOutEmpIdList = new ArrayList<>();
            for (User user: userList) {
                punchInEmpIdList.add(user.getId());
                punchOutEmpIdList.add(user.getId());
            }
            if (null != punchedInList) {
                for (Long empId : punchedInList) {
                    punchInEmpIdList.remove(empId);
                }
            }
            if (null != punchedOutList) {
                for (Long empId : punchedOutList) {
                    punchOutEmpIdList.remove(empId);
                }
            }
            for (Long empId : punchInEmpIdList) {
                AttencePunchDetail attencePunchDetail = new AttencePunchDetail();
                attencePunchDetail.setEmpId(empId);
                attencePunchDetail.setPunchInStatus(PunchInEnum.MISS.getCode());
                attencePunchDetail.setPunchTime(punchTime);
                if (punchOutEmpIdList.contains(empId)) {
                    attencePunchDetail.setPunchOutStatus(PunchOutEnum.MISS.getCode());
                    int result = attencePunchDetailMapper.insertSelective(attencePunchDetail);
                    if (result == 1) {
                        punchOutEmpIdList.remove(empId);
                        redisTemplate.opsForSet().add(punchOutKey,empId);
                    } else {
                        logger.error("插入考勤详情数据库失败");
                    }
                } else {
                    updatePunchDetail(attencePunchDetail);
                }
                redisTemplate.opsForSet().add(punchInKey,empId);
            }
            for (Long empId : punchOutEmpIdList) {
                AttencePunchDetail attencePunchDetail = new AttencePunchDetail();
                attencePunchDetail.setEmpId(empId);
                attencePunchDetail.setPunchTime(punchTime);
                attencePunchDetail.setPunchOutStatus(PunchOutEnum.MISS.getCode());
                updatePunchDetail(attencePunchDetail);
                redisTemplate.opsForSet().add(punchInKey,empId);
            }
        }
    }

    private void updatePunchDetail(AttencePunchDetail attencePunchDetail) {
        List<PunchDetailVO> punchDetailVOList = attencePunchDetailExtMapper.selectByCondition(PunchDetailVO.builder().empId(attencePunchDetail.getEmpId()).punchTime(attencePunchDetail.getPunchTime()).build());
        if (!punchDetailVOList.isEmpty()) {
            attencePunchDetail.setId(punchDetailVOList.get(0).getId());
            int result = attencePunchDetailMapper.updateByPrimaryKeySelective(attencePunchDetail);
            if (result != 1) {
                logger.error("更新考勤详情数据库失败");
            }
        }
    }
}

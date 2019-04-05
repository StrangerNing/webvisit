package com.webvisit.service.impl;

import com.webvisit.common.enums.CustomHolidayTypeEnum;
import com.webvisit.common.enums.DefaultHolidayTypeEnum;
import com.webvisit.common.enums.LeaveTypeEnum;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.dao.AttenceHolidayCustomExtMapper;
import com.webvisit.dao.AttenceHolidayDefaultExtMapper;
import com.webvisit.dao.AttenceLeaveExtMapper;
import com.webvisit.dao.AttenceRegulationExtMapper;
import com.webvisit.dao.common.AttenceHolidayCustomMapper;
import com.webvisit.dao.common.AttenceLeaveMapper;
import com.webvisit.dao.common.AttenceRegulationMapper;
import com.webvisit.model.po.AttenceHolidayCustom;
import com.webvisit.model.po.AttenceLeave;
import com.webvisit.model.po.AttenceRegulation;
import com.webvisit.model.vo.HolidayVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.AttenceService;
import com.webvisit.utils.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/1
 */

@Service
public class AttenceServiceImpl implements AttenceService {
    @Resource
    private AttenceRegulationMapper attenceRegulationMapper;
    @Resource
    private AttenceRegulationExtMapper attenceRegulationExtMapper;
    @Resource
    private AttenceHolidayDefaultExtMapper attenceHolidayDefaultExtMapper;
    @Resource
    private AttenceHolidayCustomMapper attenceHolidayCustomMapper;
    @Resource
    private AttenceHolidayCustomExtMapper attenceHolidayCustomExtMapper;
    @Resource
    private AttenceLeaveExtMapper attenceLeaveExtMapper;
    @Resource
    private AttenceLeaveMapper attenceLeaveMapper;

    @Override
    public Boolean addRegulation(AttenceRegulation attenceRegulation) {
        return attenceRegulationMapper.insert(attenceRegulation) == 1;
    }

    @Override
    public Boolean delRegulation(UserInfoVO userInfoVO, Long regulationId) {
        AttenceRegulation attenceRegulation = attenceRegulationMapper.selectByPrimaryKey(regulationId);
        if (null != attenceRegulation) {
            if (null != userInfoVO) {
                Long loginCompanyId = userInfoVO.getCompanyId();
                Long regulationCompanyId = attenceRegulation.getCompanyId();
                if (null != loginCompanyId && null != regulationCompanyId) {
                    if (loginCompanyId.equals(regulationCompanyId)) {
                        return attenceRegulationMapper.deleteByPrimaryKey(regulationId) == 1;
                    } else {
                        throw new BusinessException("你没有权限删除这个考勤规则！");
                    }
                } else {
                    throw new BusinessException("请绑定公司！");
                }
            } else {
                throw new BusinessException("获取用户信息失败，请重新登录");
            }
        } else {
            throw new BusinessException("没有查询到这个考勤规则");
        }
    }

    @Override
    public Boolean editRegulation(UserInfoVO userInfoVO, AttenceRegulation attenceRegulation) {
        Long regulationId = attenceRegulation.getId();
        AttenceRegulation queryRegulation = attenceRegulationMapper.selectByPrimaryKey(regulationId);
        if (null == queryRegulation) {
            throw new BusinessException("没有查询到这个考勤规则");
        }
        Long userCompanyId = userInfoVO.getCompanyId();
        Long queryRegulationCompanyId = queryRegulation.getCompanyId();
        if (null != queryRegulationCompanyId) {
            if (queryRegulationCompanyId.equals(userCompanyId)) {
                return attenceRegulationMapper.updateByPrimaryKey(attenceRegulation) == 1;
            } else {
                throw new BusinessException("你没有权限修改这个考勤规则！");
            }
        } else {
            throw new BusinessException("无效的考勤规则");
        }
    }

    @Override
    public List<AttenceRegulation> queryRegulations(Long companyId) {
        return attenceRegulationExtMapper.queryRegulationListByCompanyId(companyId);
    }

    @Override
    public List<HolidayVO> queryHolidays(UserInfoVO userInfoVO, Date beginDate, Date endDate) {
        return attenceHolidayCustomExtMapper.selectByDate(userInfoVO.getCompanyId(), beginDate, endDate);
    }

    @Override
    public Boolean setHoliday(UserInfoVO userInfoVO, Date date) {
        List<HolidayVO> holidays = attenceHolidayCustomExtMapper.selectByDate(userInfoVO.getCompanyId(), date, date);
        if (holidays.isEmpty()) {
            AttenceHolidayCustom custom = generateCustomHoliday(userInfoVO, date, CustomHolidayTypeEnum.NEW.getCode());
            return attenceHolidayCustomMapper.insert(custom) == 1;
        } else if (holidays.size() == 1) {
            HolidayVO holiday = holidays.get(0);
            if (holiday.getDefaultType().equals(DefaultHolidayTypeEnum.DUTY_DAY.getCode())) {
                if (null == holiday.getCustomType()) {
                    AttenceHolidayCustom custom = generateCustomHoliday(userInfoVO, date, CustomHolidayTypeEnum.NEW.getCode());
                    return attenceHolidayCustomMapper.insert(custom) == 1;
                }
            } else if (holiday.getDefaultType().equals(DefaultHolidayTypeEnum.LEGAL_HOLIDAY.getCode())) {
                if (null != holiday.getCustomType()) {
                    if (holiday.getCustomType().equals(CustomHolidayTypeEnum.CANCEL.getCode())) {
                        AttenceHolidayCustom custom = new AttenceHolidayCustom();
                        custom.setCompanyId(userInfoVO.getCompanyId());
                        custom.setHolidayDate(date);
                        return attenceHolidayCustomExtMapper.deleteByDate(custom) == 1;
                    }
                }
            }
        }
        throw new BusinessException("设置节假日失败，请确认状态后重试");
    }

    private AttenceHolidayCustom generateCustomHoliday(UserInfoVO userInfoVO, Date date, Integer customHolidayType) {
        AttenceHolidayCustom custom = new AttenceHolidayCustom();
        custom.setHolidayDate(date);
        custom.setCompanyId(userInfoVO.getCompanyId());
        custom.setType(customHolidayType);
        custom.setCreateTime(TimeUtil.createNowTime());
        custom.setCreateAccountId(userInfoVO.getId());
        return custom;
    }

    @Override
    public Boolean cancelHoliday(UserInfoVO userInfoVO, Date date) {
        List<HolidayVO> holidays = attenceHolidayCustomExtMapper.selectByDate(userInfoVO.getCompanyId(), date, date);
        if (!holidays.isEmpty()) {
            if (holidays.size() == 1) {
                HolidayVO holiday = holidays.get(0);
                if (holiday.getDefaultType().equals(DefaultHolidayTypeEnum.LEGAL_HOLIDAY.getCode())) {
                    if (null == holiday.getCustomType()) {
                        AttenceHolidayCustom custom = generateCustomHoliday(userInfoVO, date, CustomHolidayTypeEnum.CANCEL.getCode());
                        return attenceHolidayCustomMapper.insert(custom) == 1;
                    }
                } else if (holiday.getDefaultType().equals(DefaultHolidayTypeEnum.DUTY_DAY.getCode())) {
                    if (null != holiday.getCustomType()) {
                        if (holiday.getCustomType().equals(CustomHolidayTypeEnum.NEW.getCode())) {
                            AttenceHolidayCustom custom = new AttenceHolidayCustom();
                            custom.setCompanyId(userInfoVO.getCompanyId());
                            custom.setHolidayDate(date);
                            return attenceHolidayCustomExtMapper.deleteByDate(custom) == 1;
                        }
                    }
                }
            }
        }
        throw new BusinessException("取消节假日失败，请确认状态后重试");
    }

    @Override
    public List<AttenceLeave> queryLeave(UserInfoVO userInfoVO) {
        AttenceLeave attenceLeave = new AttenceLeave();
        attenceLeave.setCompanyId(userInfoVO.getCompanyId());
        return attenceLeaveExtMapper.selectByCondition(attenceLeave);
    }

    @Override
    public Boolean addLeave(UserInfoVO userInfoVO, AttenceLeave attenceLeave) {
        AttenceLeave queryLeave = new AttenceLeave();
        if (StringUtils.isEmpty(attenceLeave.getName())){
            throw new BusinessException("请假类型名必填！");
        }
        if (null == attenceLeave.getAvailableDays()){
            throw new BusinessException("可请假天数不能为空！");
        }
        if (null == attenceLeave.getSalaryPercent()){
            throw new BusinessException("薪资比例不能为空！");
        }
        queryLeave.setName(attenceLeave.getName());
        queryLeave.setCompanyId(userInfoVO.getCompanyId());
        queryLeave.setType(LeaveTypeEnum.COMPANY_ADD.getCode());
        List<AttenceLeave> attenceLeaveList = attenceLeaveExtMapper.selectByCondition(queryLeave);
        if (!attenceLeaveList.isEmpty()){
            throw new BusinessException("请假类型名不能重复！");
        }
        attenceLeave.setCompanyId(userInfoVO.getCompanyId());
        attenceLeave.setType(LeaveTypeEnum.COMPANY_ADD.getCode());
        attenceLeave.setCreateAccountId(userInfoVO.getId());
        attenceLeave.setCreateTime(TimeUtil.createNowTime());
        return attenceLeaveMapper.insert(attenceLeave) == 1;
    }

    @Override
    public Boolean deleteLeave(UserInfoVO userInfoVO, Long leaveId) {
        AttenceLeave attenceLeave = attenceLeaveMapper.selectByPrimaryKey(leaveId);
        if (null == attenceLeave) {
            throw new BusinessException("没有查询到此请假类型");
        }
        Long leaveCompanyId = attenceLeave.getCompanyId();
        if (null == leaveCompanyId){
            throw new BusinessException("此请假类型无效");
        }
        if (!leaveCompanyId.equals(userInfoVO.getCompanyId())){
            throw new BusinessException("您没有权限删除此请假类型！");
        }
        return attenceLeaveMapper.deleteByPrimaryKey(leaveId) == 1;
    }
}

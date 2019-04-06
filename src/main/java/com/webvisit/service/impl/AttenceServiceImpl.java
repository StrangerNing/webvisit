package com.webvisit.service.impl;

import com.webvisit.common.enums.AnnualBaseEnum;
import com.webvisit.common.enums.CustomHolidayTypeEnum;
import com.webvisit.common.enums.DefaultHolidayTypeEnum;
import com.webvisit.common.enums.LeaveTypeEnum;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.dao.*;
import com.webvisit.dao.common.*;
import com.webvisit.model.po.*;
import com.webvisit.model.vo.AnnualStepVO;
import com.webvisit.model.vo.AnnualVO;
import com.webvisit.model.vo.HolidayVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.AttenceService;
import com.webvisit.utils.TimeUtil;
import com.webvisit.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private AttenceAnnualMapper attenceAnnualMapper;
    @Resource
    private AttenceAnnualExtMapper attenceAnnualExtMapper;
    @Resource
    private AttenceAnnualStepMapper attenceAnnualStepMapper;
    @Resource
    private AttenceAnnualStepExtMapper attenceAnnualStepExtMapper;

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
        if (StringUtils.isEmpty(attenceLeave.getName())) {
            throw new BusinessException("请假类型名必填！");
        }
        if (null == attenceLeave.getAvailableDays()) {
            throw new BusinessException("可请假天数不能为空！");
        }
        if (null == attenceLeave.getSalaryPercent()) {
            throw new BusinessException("薪资比例不能为空！");
        }
        queryLeave.setName(attenceLeave.getName());
        queryLeave.setCompanyId(userInfoVO.getCompanyId());
        queryLeave.setType(LeaveTypeEnum.COMPANY_ADD.getCode());
        List<AttenceLeave> attenceLeaveList = attenceLeaveExtMapper.selectByCondition(queryLeave);
        if (!attenceLeaveList.isEmpty()) {
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
        if (null == leaveCompanyId) {
            throw new BusinessException("此请假类型无效");
        }
        if (!leaveCompanyId.equals(userInfoVO.getCompanyId())) {
            throw new BusinessException("您没有权限删除此请假类型！");
        }
        return attenceLeaveMapper.deleteByPrimaryKey(leaveId) == 1;
    }

    @Override
    public List<AttenceAnnual> queryAnnul(UserInfoVO userInfoVO) {
        AttenceAnnual attenceAnnual = new AttenceAnnual();
        attenceAnnual.setCompanyId(userInfoVO.getCompanyId());
        return attenceAnnualExtMapper.selectByCondition(attenceAnnual);
    }

    @Override
    public Boolean deleteAnnul(UserInfoVO userInfoVO, Long annulId) {
        AttenceAnnual queryAnnul = attenceAnnualMapper.selectByPrimaryKey(annulId);
        if (null == queryAnnul) {
            throw new BusinessException("没有查询到此年假类型");
        }
        Long annulCompanyId = queryAnnul.getCompanyId();
        if (null == annulCompanyId) {
            throw new BusinessException("此请假类型无效");
        }
        if (!annulCompanyId.equals(userInfoVO.getCompanyId())) {
            throw new BusinessException("您没有权限删除此年假类型！");
        }
        if (queryAnnul.getStatus().equals(AnnualBaseEnum.AnnualStatusEnum.ENABLE.getCode())) {
            throw new BusinessException("已启用的年假类型无法删除！");
        }
        if (attenceAnnualStepExtMapper.deleteByAnnualId(annulId) == 0) {
            throw new BusinessException("删除年假类型出错！");
        }
        return attenceAnnualMapper.deleteByPrimaryKey(annulId) == 1;
    }

    @Override
    public Boolean addAnnual(UserInfoVO userInfoVO, AnnualVO annualVO) {
        ValidatorUtil.validate(annualVO);
        AttenceAnnual queryCondition = new AttenceAnnual();
        queryCondition.setCompanyId(userInfoVO.getCompanyId());
        queryCondition.setStatus(AnnualBaseEnum.AnnualStatusEnum.ENABLE.getCode());
        List<AttenceAnnual> queryAnnuals = attenceAnnualExtMapper.selectByCondition(queryCondition);
        if (queryAnnuals.size() > 1) {
            throw new BusinessException("查询出错");
        }
        if (annualVO.getStatus().equals(AnnualBaseEnum.AnnualStatusEnum.ENABLE.getCode())) {
            if (queryAnnuals.size() == 1) {
                AttenceAnnual setAnnual = new AttenceAnnual();
                setAnnual.setId(queryAnnuals.get(0).getId());
                setAnnual.setStatus(AnnualBaseEnum.AnnualStatusEnum.DISABLE.getCode());
                setAnnual.setModifyAccountId(userInfoVO.getId());
                setAnnual.setModifyTime(TimeUtil.createNowTime());
                attenceAnnualMapper.updateByPrimaryKeySelective(setAnnual);
            }
        }
        AttenceAnnual attenceAnnual = new AttenceAnnual();
        BeanUtils.copyProperties(annualVO, attenceAnnual);
        attenceAnnual.setCompanyId(userInfoVO.getCompanyId());
        attenceAnnual.setCreateAccountId(userInfoVO.getId());
        attenceAnnual.setCreateTime(TimeUtil.createNowTime());
        int insertNum =  attenceAnnualExtMapper.insertSelectiveReturnId(attenceAnnual);
        AttenceAnnualStep attenceAnnualStep = new AttenceAnnualStep();
        attenceAnnualStep.setAnnualId(attenceAnnual.getId());
        attenceAnnualStep.setMoreThan(1);
        attenceAnnualStep.setLessThan(100);
        attenceAnnualStep.setVacationDays(10);
        int insertStepNum = attenceAnnualStepMapper.insert(attenceAnnualStep);
        return insertNum == 1 && insertStepNum == 1;
    }

    @Override
    public Boolean editAnnual(UserInfoVO userInfoVO, AnnualVO annualVO) {
        if (null == annualVO.getId()){
            throw new BusinessException("年假规则id不可为空！");
        }
        AttenceAnnual attenceAnnual = attenceAnnualMapper.selectByPrimaryKey(annualVO.getId());
        if (!attenceAnnual.getCompanyId().equals(userInfoVO.getCompanyId())){
            throw new BusinessException("您没有权限编辑此年假类型！");
        }
        AttenceAnnual editAnnual = new AttenceAnnual();
        BeanUtils.copyProperties(annualVO,editAnnual);
        return attenceAnnualMapper.updateByPrimaryKeySelective(editAnnual) == 1;
    }

    @Override
    public List<AttenceAnnualStep> queryAnnualStep(UserInfoVO userInfoVO, Long annualId) {
        AttenceAnnual queryAnnual = attenceAnnualMapper.selectByPrimaryKey(annualId);
        if (null == queryAnnual){
            throw new BusinessException("没有查询到此年假规则");
        }
        Long companyId = queryAnnual.getCompanyId();
        if (null == companyId){
            throw new BusinessException("该年假规则无效！");
        }
        if (!companyId.equals(userInfoVO.getCompanyId())){
            throw new BusinessException("您没有权限查看该年假规则");
        }
        return attenceAnnualStepExtMapper.selectByAnnualId(annualId);
    }

    @Override
    public Boolean addAnnualStep(UserInfoVO userInfoVO, AnnualStepVO annualStepVO) {
        return null;
    }
}

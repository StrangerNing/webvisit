package com.webvisit.service.impl;

import com.webvisit.common.enums.AnnualBaseEnum;
import com.webvisit.common.enums.CustomHolidayTypeEnum;
import com.webvisit.common.enums.DefaultHolidayTypeEnum;
import com.webvisit.common.enums.LeaveTypeEnum;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.dao.*;
import com.webvisit.dao.common.*;
import com.webvisit.model.dto.UserSimpleDTO;
import com.webvisit.model.po.*;
import com.webvisit.model.vo.*;
import com.webvisit.service.AttenceService;
import com.webvisit.utils.TimeUtil;
import com.webvisit.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/1
 */

@Service
public class AttenceServiceImpl implements AttenceService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserExtMapper userExtMapper;
    @Resource
    private AttenceRegulationMapper attenceRegulationMapper;
    @Resource
    private AttenceRegulationExtMapper attenceRegulationExtMapper;
    @Resource
    private AttenceWorkdayMapper attenceWorkdayMapper;
    @Resource
    private AttenceWorkdayExtMapper attenceWorkdayExtMapper;
    @Resource
    private AttenceHolidayDefaultExtMapper attenceHolidayDefaultExtMapper;
    @Resource
    private AttenceHolidayCustomMapper attenceHolidayCustomMapper;
    @Resource
    private AttenceHolidayCustomExtMapper attenceHolidayCustomExtMapper;
    @Resource
    private AttenceHolidayDetailMapper attenceHolidayDetailMapper;
    @Resource
    private AttenceHolidayDetailExtMapper attenceHolidayDetailExtMapper;
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
    @Resource
    private AttenceReportExtMapper attenceReportExtMapper;
    @Resource
    private AttencePunchDetailExtMapper attencePunchDetailExtMapper;

    @Override
    public Boolean addRegulation(AttenceRegulation attenceRegulation) {
        int insertRegulationResult = attenceRegulationExtMapper.insertSelectiveReturnId(attenceRegulation);
        Long regulationId = attenceRegulation.getId();
        for (int i = 1; i < 6; i++) {
            AttenceWorkday attenceWorkday = new AttenceWorkday();
            attenceWorkday.setRegulationId(regulationId);
            attenceWorkday.setWeekDay(i);
            if(attenceWorkdayMapper.insert(attenceWorkday) !=1){
                throw new BusinessException("插入默认工作日失败");
            }
        }
        return insertRegulationResult == 1;
    }

    @Override
    public Boolean delRegulation(UserInfoVO userInfoVO, Long regulationId) {
        AttenceRegulation attenceRegulation = attenceRegulationMapper.selectByPrimaryKey(regulationId);
        if (null == attenceRegulation) {
            throw new BusinessException("没有查询到这个考勤规则");
        }
        Long companyId = attenceRegulation.getCompanyId();
        if (null == companyId) {
            throw new BusinessException("该考勤规则无效");
        }
        if (!companyId.equals(userInfoVO.getCompanyId())) {
            throw new BusinessException("您没有权限删除这个考勤规则！");
        }
        int deleteRegulationResult = attenceRegulationMapper.deleteByPrimaryKey(regulationId);
        int deleteWorkdayResult = attenceWorkdayExtMapper.deleteByRegulationId(regulationId);
        return deleteRegulationResult == 1 && deleteWorkdayResult > 0;
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
    public List<AttenceWorkday> queryWorkDays(UserInfoVO userInfoVO, Long regulationId) {
        checkRelatedRegulation(userInfoVO, regulationId);
        return attenceWorkdayExtMapper.selectByRegulationId(regulationId);
    }

    @Override
    public Boolean setWorkday(UserInfoVO userInfoVO, WorkdayVO workdayVO) {
        ValidatorUtil.validate(workdayVO);
        List<Integer> workdayList = workdayVO.getWorkdayList();
        if (Collections.max(workdayList) > 7 || Collections.min(workdayList) < 1) {
            throw new BusinessException("工作日设置出错");
        }
        Long regulationId = workdayVO.getRegulationId();
        if (attenceWorkdayExtMapper.deleteByRegulationId(regulationId) == 0) {
            throw new BusinessException("工作日设置出错");
        }
        Set<Integer> noDuplicateList = new HashSet<Integer>();
        for (Integer workday : workdayList) {
            if (noDuplicateList.add(workday)) {
                AttenceWorkday attenceWorkday = new AttenceWorkday();
                attenceWorkday.setRegulationId(regulationId);
                attenceWorkday.setWeekDay(workday);
                if (attenceWorkdayMapper.insert(attenceWorkday) != 1) {
                    throw new BusinessException("设置工作日出错");
                }
            }
        }
        return true;
    }

    private void checkRelatedRegulation(UserInfoVO userInfoVO, Long regulationId) {
        AttenceRegulation regulation = attenceRegulationMapper.selectByPrimaryKey(regulationId);
        if (null == regulation) {
            throw new BusinessException("没有查询到关联的考勤规则");
        }
        Long companyId = regulation.getCompanyId();
        if (null == companyId) {
            throw new BusinessException("关联的考勤规则无效！");
        }
        if (!companyId.equals(userInfoVO.getCompanyId())) {
            throw new BusinessException("您没有权限操作该考勤规则！");
        }
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
        attenceLeave.setType(LeaveTypeEnum.COMPANY_ADD.getCode());
        return attenceLeaveExtMapper.selectByCondition(attenceLeave);
    }

    @Override
    public Boolean addLeave(UserInfoVO userInfoVO, LeaveVO leaveVO) {
        AttenceLeave queryLeave = new AttenceLeave();
        ValidatorUtil.validate(leaveVO);
        AttenceLeave attenceLeave = new AttenceLeave();
        BeanUtils.copyProperties(leaveVO,attenceLeave);
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
        AttenceLeave setLeave = new AttenceLeave();
        setLeave.setId(leaveId);
        setLeave.setType(LeaveTypeEnum.COMPANY_DELETE.getCode());
        setLeave.setModifyAccountId(userInfoVO.getId());
        setLeave.setModifyTime(TimeUtil.createNowTime());
        return attenceLeaveMapper.updateByPrimaryKeySelective(setLeave) == 1;
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
        int insertNum = attenceAnnualExtMapper.insertSelectiveReturnId(attenceAnnual);
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
        if (null == annualVO.getId()) {
            throw new BusinessException("年假规则id不可为空！");
        }
        AttenceAnnual attenceAnnual = attenceAnnualMapper.selectByPrimaryKey(annualVO.getId());
        if (!attenceAnnual.getCompanyId().equals(userInfoVO.getCompanyId())) {
            throw new BusinessException("您没有权限编辑此年假类型！");
        }
        AttenceAnnual editAnnual = new AttenceAnnual();
        BeanUtils.copyProperties(annualVO, editAnnual);
        return attenceAnnualMapper.updateByPrimaryKeySelective(editAnnual) == 1;
    }

    @Override
    public List<AttenceAnnualStep> queryAnnualStep(UserInfoVO userInfoVO, Long annualId) {
        AttenceAnnual queryAnnual = attenceAnnualMapper.selectByPrimaryKey(annualId);
        if (null == queryAnnual) {
            throw new BusinessException("没有查询到关联的年假规则");
        }
        Long companyId = queryAnnual.getCompanyId();
        if (null == companyId) {
            throw new BusinessException("该年假规则无效！");
        }
        if (!companyId.equals(userInfoVO.getCompanyId())) {
            throw new BusinessException("您没有权限查看该年假阶梯设置");
        }
        return attenceAnnualStepExtMapper.selectByAnnualId(annualId);
    }

    @Override
    public Boolean addAnnualStep(UserInfoVO userInfoVO, AnnualStepVO annualStepVO) {
        ValidatorUtil.validate(annualStepVO);
        AttenceAnnual queryAnnual = attenceAnnualMapper.selectByPrimaryKey(annualStepVO.getAnnualId());
        if (null == queryAnnual) {
            throw new BusinessException("没有查询到此年假规则");
        }
        Long companyId = queryAnnual.getCompanyId();
        if (null == companyId) {
            throw new BusinessException("该年假规则无效！");
        }
        if (!companyId.equals(userInfoVO.getCompanyId())) {
            throw new BusinessException("您没有权限修改该年假阶梯设置！");
        }
        AttenceAnnualStep attenceAnnualStep = new AttenceAnnualStep();
        BeanUtils.copyProperties(annualStepVO, attenceAnnualStep);
        return attenceAnnualStepMapper.insert(attenceAnnualStep) == 1;
    }

    @Override
    public Boolean deleteAnnualStep(UserInfoVO userInfoVO, Long annualStepId) {
        checkAnnualStep(userInfoVO, annualStepId);
        return attenceAnnualStepMapper.deleteByPrimaryKey(annualStepId) == 1;
    }

    private void checkAnnualStep(UserInfoVO userInfoVO, Long annualStepId) {
        AttenceAnnualStep queryAnnualStep = attenceAnnualStepMapper.selectByPrimaryKey(annualStepId);
        if (null == queryAnnualStep) {
            throw new BusinessException("没有查询到此年假阶梯设置");
        }
        Long annualId = queryAnnualStep.getAnnualId();
        if (null == annualId) {
            throw new BusinessException("该年假阶梯设置无效！");
        }
        AttenceAnnual queryAnnual = attenceAnnualMapper.selectByPrimaryKey(annualId);
        if (null == queryAnnual) {
            throw new BusinessException("没有查询到关联的年假规则！");
        }
        Long companyId = queryAnnual.getCompanyId();
        if (null == companyId) {
            throw new BusinessException("关联的年假规则无效！");
        }
        if (!companyId.equals(userInfoVO.getCompanyId())) {
            throw new BusinessException("您没有权限操作该年假阶梯设置!");
        }
    }

    @Override
    public Boolean editAnnualStep(UserInfoVO userInfoVO, AnnualStepVO annualStepVO) {
        ValidatorUtil.validate(annualStepVO);
        checkAnnualStep(userInfoVO, annualStepVO.getAnnualId());
        AttenceAnnualStep attenceAnnualStep = new AttenceAnnualStep();
        BeanUtils.copyProperties(annualStepVO, attenceAnnualStep);
        return attenceAnnualStepMapper.updateByPrimaryKeySelective(attenceAnnualStep) == 1;
    }

    @Override
    public List<AnnualDetailVO> queryAnnualDetail(UserInfoVO userInfoVO) {
        Long companyId = userInfoVO.getCompanyId();
        List<UserSimpleDTO> userList = userExtMapper.selectByCompanyId(companyId);
        if (userList.isEmpty()){
            throw new BusinessException("没有查询到员工");
        }
        List<AnnualDetailVO> annualDetailList = new ArrayList<>();
        for (UserSimpleDTO userSimple : userList){
            Long empId = userSimple.getId();
            List<AttenceHolidayDetail> holidayDetailList = attenceHolidayDetailExtMapper.selectByEmpId(empId);
            AnnualDetailVO annualDetailVO = new AnnualDetailVO();
            BeanUtils.copyProperties(userSimple,annualDetailVO);
            annualDetailVO.setHolidayDetailList(holidayDetailList);
            annualDetailList.add(annualDetailVO);
        }
        return annualDetailList;
    }

    @Override
    public Boolean editAnnualDetail(UserInfoVO userInfoVO, HolidayDetailVO holidayDetailVO) {
        ValidatorUtil.validate(holidayDetailVO);
        AttenceHolidayDetail queryDetail = attenceHolidayDetailMapper.selectByPrimaryKey(holidayDetailVO.getId());
        Long empId = queryDetail.getEmpId();
        User user = userMapper.selectByPrimaryKey(empId);
        if (null == user){
            throw new BusinessException("没有查询到该员工");
        }
        Long companyId = user.getCompanyId();
        if (null == companyId){
            throw new BusinessException("请为该员工绑定公司");
        }
        if (!companyId.equals(userInfoVO.getCompanyId())){
            throw new BusinessException("您没有权限操作该员工信息");
        }
        AttenceHolidayDetail attenceHolidayDetail = new AttenceHolidayDetail();
        BeanUtils.copyProperties(holidayDetailVO,attenceHolidayDetail);
        return attenceHolidayDetailMapper.updateByPrimaryKeySelective(attenceHolidayDetail) == 1;
    }

    @Override
    public List<AttenceReportVO> queryAttenceReport(UserInfoVO userInfoVO) {
        Long companyId = userInfoVO.getCompanyId();
        return attenceReportExtMapper.selectByCompanyId(companyId);
    }

    @Override
    public List<PunchDetailVO> queryAttencePunchDetail(UserInfoVO userInfoVO, PunchDetailVO punchDetailVO) {
        Long empId = punchDetailVO.getEmpId();
        if (null != empId) {
            User user = userMapper.selectByPrimaryKey(empId);
            if (null == user) {
                throw new BusinessException("没有找到该员工");
            }
            Long companyId = user.getCompanyId();
            if (null == user.getCompanyId()) {
                throw new BusinessException("请先为该员工绑定公司");
            }
            if (!companyId.equals(userInfoVO.getCompanyId())) {
                throw new BusinessException("您没有权限查看该员工的考勤信息");
            }
        }
        punchDetailVO.setCompanyId(userInfoVO.getCompanyId());
        return attencePunchDetailExtMapper.selectByCondition(punchDetailVO);
    }

}

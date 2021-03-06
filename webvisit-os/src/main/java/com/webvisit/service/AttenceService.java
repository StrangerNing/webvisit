package com.webvisit.service;

import com.github.pagehelper.PageInfo;
import com.webvisit.model.dto.RegulationDTO;
import com.webvisit.model.po.AttenceAnnualStep;
import com.webvisit.model.po.AttenceLeave;
import com.webvisit.model.po.AttenceWorkday;
import com.webvisit.model.po.CompanyDept;
import com.webvisit.model.vo.*;

import java.util.Date;
import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/1
 */
public interface AttenceService {

    /**
     * 新增考勤规则
     *
     * @param regulationDTO 考勤规则
     * @return 新增结果
     */
    Boolean addRegulation(RegulationDTO regulationDTO);

    /**
     * 删除考勤规则
     *
     * @param userInfoVO   当前用户信息
     * @param regulationId 规则id
     * @return 操作结果
     */
    Boolean delRegulation(UserInfoVO userInfoVO, Long regulationId);

    /**
     * 修改考勤规则
     *
     * @param userInfoVO        当前用户信息
     * @param regulationDTO 考勤规则
     * @return 操作结果
     */
    Boolean editRegulation(UserInfoVO userInfoVO, RegulationDTO regulationDTO);

    /**
     * 根据公司id查询考勤规则
     *
     * @param companyId 公司id
     * @return 操作结果
     */
    List<RegulationVO> queryRegulations(Long companyId);

    /**
     * 查看工作日设置
     *
     * @param userInfoVO   当前用户信息
     * @param regulationId 考勤规则id
     * @return 工作日列表
     */
    List<AttenceWorkday> queryWorkDays(UserInfoVO userInfoVO, Long regulationId);

    /**
     * 设置工作日
     *
     * @param userInfoVO 当前用户信息
     * @param workdayVO  工作日列表
     * @return 设置结果
     */
    Boolean setWorkday(UserInfoVO userInfoVO, WorkdayVO workdayVO);

    /**
     * 根据公司id查询节假日
     *
     * @param userInfoVO 当前用户信息
     * @param beginDate  开始时间
     * @param endDate    结束时间
     * @return 节假日列表
     */
    List<HolidayVO> queryHolidays(UserInfoVO userInfoVO, Date beginDate, Date endDate);

    /**
     * 根据日期设置节假日
     *
     * @param date       日期
     * @param userInfoVO 当前用户信息
     * @return 设置结果
     */
    Boolean setHoliday(UserInfoVO userInfoVO, Date date);

    /**
     * 根据日期取消节假日
     *
     * @param userInfoVO 当前用户信息
     * @param date       日期
     * @return 设置结果
     */
    Boolean cancelHoliday(UserInfoVO userInfoVO, Date date);

    /**
     * 查询请假类型
     *
     * @param userInfoVO 当前用户信息
     * @return 请假类型列表
     */
    List<AttenceLeave> queryLeave(UserInfoVO userInfoVO);

    /**
     * 添加请假类型
     *
     * @param userInfoVO 当前用户信息
     * @param leaveVO    请假类型
     * @return 添加结果
     */
    Boolean addLeave(UserInfoVO userInfoVO, LeaveVO leaveVO);

    /**
     * 编辑请假类型
     * @param userInfoVO 当前用户信息
     * @param leaveVO 请假类型
     * @return 编辑结果
     */
    Boolean editLeave(UserInfoVO userInfoVO, LeaveVO leaveVO);

    /**
     * 删除请假类型
     *
     * @param userInfoVO 当前用户信息
     * @param leaveId    请假类型id
     * @return 操作结果
     */
    Boolean deleteLeave(UserInfoVO userInfoVO, Long leaveId);

    /**
     * 查询年假规则
     *
     * @param userInfoVO 当前用户信息
     * @return 操作结果
     */
    List<AnnualVO> queryAnnul(UserInfoVO userInfoVO);

    /**
     * 删除年假规则
     *
     * @param userInfoVO 当前用户信息
     * @param annulId    年假规则id
     * @return 删除结果
     */
    Boolean deleteAnnul(UserInfoVO userInfoVO, Long annulId);

    /**
     * 增加年假规则
     *
     * @param userInfoVO 当前用户信息
     * @param annualVO   年假规则信息
     * @return 增加结果
     */
    Boolean addAnnual(UserInfoVO userInfoVO, AnnualVO annualVO);

    /**
     * 编辑年假规则
     *
     * @param userInfoVO 当前用户信息
     * @param annualVO   年假规则信息
     * @return 编辑结果
     */
    Boolean editAnnual(UserInfoVO userInfoVO, AnnualVO annualVO);

    /**
     * 查看年假规则的年假阶梯设置
     *
     * @param userInfoVO 当前用户信息
     * @param annualId   年假规则id
     * @return 年假阶梯设置列表
     */
    List<AttenceAnnualStep> queryAnnualStep(UserInfoVO userInfoVO, Long annualId);

    /**
     * 增加年假规则的年假阶梯设置
     *
     * @param userInfoVO   当前用户信息
     * @param annualStepVO 年假阶梯设置
     * @return 操作结果
     */
    Boolean addAnnualStep(UserInfoVO userInfoVO, AnnualStepVO annualStepVO);

    /**
     * 根据年假阶梯设置id删除年假阶梯设置
     *
     * @param userInfoVO   当前用户信息
     * @param annualStepId 年假阶梯设置id
     * @return 删除结果
     */
    Boolean deleteAnnualStep(UserInfoVO userInfoVO, Long annualStepId);

    /**
     * 修改年假阶梯设置
     *
     * @param userInfoVO   当前用户信息
     * @param annualStepVO 年假阶梯设置
     * @return 修改结果
     */
    Boolean editAnnualStep(UserInfoVO userInfoVO, AnnualStepVO annualStepVO);

    /**
     * 查询员工年假信息
     *
     * @param userInfoVO 当前用户信息
     * @return 员工年假信息
     */
    List<AnnualDetailVO> queryAnnualDetail(UserInfoVO userInfoVO);

    /**
     * 编辑员工年假天数
     * @param userInfoVO 当前用户信息
     * @param holidayDetailVO 假期设置
     * @return 编辑结果
     */
    Boolean editAnnualDetail(UserInfoVO userInfoVO, HolidayDetailVO holidayDetailVO);

    /**
     * 查询考勤统计总报
     * @param userInfoVO 当前用户信息
     * @param attenceReportVO 查询条件
     * @return 考勤统计
     */
    PageInfo<AttenceReportVO> queryAttenceReport(UserInfoVO userInfoVO, AttenceReportVO attenceReportVO);

    /**
     * 根据员工id获取考勤详情
     * @param userInfoVO 当前用户信息
     * @param punchDetailVO 查询条件
     * @return 考勤详情
     */
    PageInfo<PunchDetailVO> queryAttencePunchDetail(UserInfoVO userInfoVO, PunchDetailVO punchDetailVO);

    List<PunchDetailVO> queryAttencePunchDetailNoPaged(UserInfoVO userInfoVO, PunchDetailVO punchDetailVO);

    /**
     * 查询公司所有部门列表
     * @param userInfoVO 当前用户信息
     * @param companyDept 查询条件
     * @return 部门列表
     */
    List<CompanyDept> queryDeptList(UserInfoVO userInfoVO,CompanyDept companyDept);
//
//    /**
//     * 获取公司职员列表
//     * @param userInfoVO 当前用户信息
//     * @return 职员列表
//     */
//    List<UserSimpleDTO> queryEmpList(UserInfoVO userInfoVO);
}

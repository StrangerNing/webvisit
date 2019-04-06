package com.webvisit.service;

import com.webvisit.model.po.AttenceAnnual;
import com.webvisit.model.po.AttenceAnnualStep;
import com.webvisit.model.po.AttenceLeave;
import com.webvisit.model.po.AttenceRegulation;
import com.webvisit.model.vo.AnnualStepVO;
import com.webvisit.model.vo.AnnualVO;
import com.webvisit.model.vo.HolidayVO;
import com.webvisit.model.vo.UserInfoVO;

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
     * @param attenceRegulation 考勤规则
     * @return 新增结果
     */
    Boolean addRegulation(AttenceRegulation attenceRegulation);

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
     * @param attenceRegulation 考勤规则
     * @return 操作结果
     */
    Boolean editRegulation(UserInfoVO userInfoVO, AttenceRegulation attenceRegulation);

    /**
     * 根据公司id查询考勤规则
     *
     * @param companyId 公司id
     * @return 操作结果
     */
    List<AttenceRegulation> queryRegulations(Long companyId);

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
     * @param userInfoVO   当前用户信息
     * @param attenceLeave 请假类型
     * @return 添加结果
     */
    Boolean addLeave(UserInfoVO userInfoVO, AttenceLeave attenceLeave);

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
    List<AttenceAnnual> queryAnnul(UserInfoVO userInfoVO);

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
     * @param userInfoVO 当前用户信息
     * @param annualStepVO 年假阶梯设置
     * @return 操作结果
     */
    Boolean addAnnualStep(UserInfoVO userInfoVO, AnnualStepVO annualStepVO);

    /**
     * 根据年假阶梯设置id删除年假阶梯设置
     * @param userInfoVO 当前用户信息
     * @param annualStepId 年假阶梯设置id
     * @return 删除结果
     */
    Boolean deleteAnnualStep(UserInfoVO userInfoVO,Long annualStepId);

    /**
     * 修改年假阶梯设置
     * @param userInfoVO 当前用户信息
     * @param annualStepVO 年假阶梯设置
     * @return 修改结果
     */
    Boolean editAnnualStep(UserInfoVO userInfoVO,AnnualStepVO annualStepVO);

}

package com.webvisit.service;

import com.webvisit.model.po.AttenceRegulation;
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
     * @param userInfoVO 当前用户信息
     * @param date 日期
     * @return 设置结果
     */
    Boolean cancelHoliday(UserInfoVO userInfoVO, Date date);
}

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
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 节假日列表
     */
    HolidayVO queryHolidays(UserInfoVO userInfoVO, Date beginDate, Date endDate);
}

package com.webvisit.service;

import com.webvisit.model.po.AttenceRegulation;

import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/1
 */
public interface AttenceService {

    /**
     * 新增考勤规则
     * @param attenceRegulation 考勤规则
     * @return 新增结果
     */
    Boolean addRegulation(AttenceRegulation attenceRegulation);

    /**
     * 删除考勤规则
     * @param id 规则id
     * @return 操作结果
     */
    Boolean delRegulation(Long id);

    /**
     * 修改考勤规则
     * @param attenceRegulation 考勤规则
     * @return 操作结果
     */
    Boolean editRegulation(AttenceRegulation attenceRegulation);

    /**
     * 根据公司id查询考勤规则
     * @param companyId 公司id
     * @return 操作结果
     */
    List<AttenceRegulation> queryRequlations(Long companyId);
}

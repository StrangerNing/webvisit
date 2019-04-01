package com.webvisit.dao;

import com.webvisit.model.po.AttenceRegulation;

import java.util.List;

/**
 * @author Tsening Chu
 * @version 1.0
 * @date 2019/4/1
 */
public interface AttenceRegulationExtMapper {

    /**
     * 根据公司id获取考勤规则列表
     * @param companyId 公司id
     * @return 考勤规则列表
     */
    List<AttenceRegulation> queryRegulationListByCompanyId(Long companyId);

}

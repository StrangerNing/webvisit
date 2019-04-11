package com.webvisit.dao;

import com.webvisit.model.po.AttenceRegulation;
import com.webvisit.model.vo.RegulationVO;

import java.util.List;

/**
 * @author Tsening Chu
 * @version 1.0
 * @date 2019/4/1
 */
public interface AttenceRegulationExtMapper {

    /**
     * 根据公司id获取考勤规则列表
     *
     * @param companyId 公司id
     * @return 考勤规则列表
     */
    List<RegulationVO> queryRegulationListByCompanyId(Long companyId);

    /**
     * 插入考勤规则返回主键id
     *
     * @param attenceRegulation 考勤规则
     * @return 影响条数
     */
    int insertSelectiveReturnId(AttenceRegulation attenceRegulation);

}

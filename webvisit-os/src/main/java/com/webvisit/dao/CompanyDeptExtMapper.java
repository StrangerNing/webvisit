package com.webvisit.dao;

import com.webvisit.model.po.CompanyDept;

import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/23
 */

public interface CompanyDeptExtMapper {

    List<CompanyDept> queryDeptByCondition(CompanyDept companyDept);
}

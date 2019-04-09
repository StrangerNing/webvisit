package com.webvisit.dao.common;

import com.webvisit.model.po.CompanyDept;

public interface CompanyDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyDept record);

    int insertSelective(CompanyDept record);

    CompanyDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyDept record);

    int updateByPrimaryKey(CompanyDept record);
}
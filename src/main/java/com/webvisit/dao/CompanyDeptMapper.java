package com.webvisit.dao;

import com.webvisit.model.CompanyDept;

public interface CompanyDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyDept record);

    int insertSelective(CompanyDept record);

    CompanyDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyDept record);

    int updateByPrimaryKey(CompanyDept record);
}
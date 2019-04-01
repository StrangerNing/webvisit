package com.webvisit.dao;

import com.webvisit.model.CompanyInfo;

public interface CompanyInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyInfo record);

    int insertSelective(CompanyInfo record);

    CompanyInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyInfo record);

    int updateByPrimaryKey(CompanyInfo record);
}
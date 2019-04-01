package com.webvisit.dao;

import com.webvisit.model.CompanyProduct;

public interface CompanyProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyProduct record);

    int insertSelective(CompanyProduct record);

    CompanyProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyProduct record);

    int updateByPrimaryKey(CompanyProduct record);
}
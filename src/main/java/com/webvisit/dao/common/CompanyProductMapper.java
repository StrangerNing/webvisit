package com.webvisit.dao.common;

import com.webvisit.model.po.CompanyProduct;

public interface CompanyProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyProduct record);

    int insertSelective(CompanyProduct record);

    CompanyProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyProduct record);

    int updateByPrimaryKey(CompanyProduct record);
}
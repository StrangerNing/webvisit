package com.webvisit.dao;

import com.webvisit.model.CompanyProductImg;

public interface CompanyProductImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyProductImg record);

    int insertSelective(CompanyProductImg record);

    CompanyProductImg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyProductImg record);

    int updateByPrimaryKey(CompanyProductImg record);
}
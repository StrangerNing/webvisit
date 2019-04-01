package com.webvisit.dao;

import com.webvisit.model.CompanyImg;

public interface CompanyImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyImg record);

    int insertSelective(CompanyImg record);

    CompanyImg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyImg record);

    int updateByPrimaryKey(CompanyImg record);
}
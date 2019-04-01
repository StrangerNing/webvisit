package com.webvisit.dao.common;

import com.webvisit.model.po.CompanyImg;

public interface CompanyImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyImg record);

    int insertSelective(CompanyImg record);

    CompanyImg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyImg record);

    int updateByPrimaryKey(CompanyImg record);
}
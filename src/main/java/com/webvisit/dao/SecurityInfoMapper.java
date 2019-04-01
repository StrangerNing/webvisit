package com.webvisit.dao;

import com.webvisit.model.SecurityInfo;

public interface SecurityInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SecurityInfo record);

    int insertSelective(SecurityInfo record);

    SecurityInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecurityInfo record);

    int updateByPrimaryKey(SecurityInfo record);
}
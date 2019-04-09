package com.webvisit.dao.common;

import com.webvisit.model.po.SecurityInfo;

public interface SecurityInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SecurityInfo record);

    int insertSelective(SecurityInfo record);

    SecurityInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecurityInfo record);

    int updateByPrimaryKey(SecurityInfo record);
}
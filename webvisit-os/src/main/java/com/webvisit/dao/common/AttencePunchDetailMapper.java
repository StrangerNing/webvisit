package com.webvisit.dao.common;

import com.webvisit.model.po.AttencePunchDetail;

public interface AttencePunchDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttencePunchDetail record);

    int insertSelective(AttencePunchDetail record);

    AttencePunchDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttencePunchDetail record);

    int updateByPrimaryKey(AttencePunchDetail record);
}
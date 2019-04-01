package com.webvisit.dao;

import com.webvisit.model.PunchDetail;

public interface PunchDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PunchDetail record);

    int insertSelective(PunchDetail record);

    PunchDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PunchDetail record);

    int updateByPrimaryKey(PunchDetail record);
}
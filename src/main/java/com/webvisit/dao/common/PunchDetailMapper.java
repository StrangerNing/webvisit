package com.webvisit.dao.common;

import com.webvisit.model.po.PunchDetail;

public interface PunchDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PunchDetail record);

    int insertSelective(PunchDetail record);

    PunchDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PunchDetail record);

    int updateByPrimaryKey(PunchDetail record);
}
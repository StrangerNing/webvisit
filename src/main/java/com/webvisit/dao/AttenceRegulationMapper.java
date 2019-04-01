package com.webvisit.dao;

import com.webvisit.model.AttenceRegulation;

public interface AttenceRegulationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceRegulation record);

    int insertSelective(AttenceRegulation record);

    AttenceRegulation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceRegulation record);

    int updateByPrimaryKey(AttenceRegulation record);
}
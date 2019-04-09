package com.webvisit.dao.common;

import com.webvisit.model.po.AttenceRegulation;

public interface AttenceRegulationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceRegulation record);

    int insertSelective(AttenceRegulation record);

    AttenceRegulation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceRegulation record);

    int updateByPrimaryKey(AttenceRegulation record);
}
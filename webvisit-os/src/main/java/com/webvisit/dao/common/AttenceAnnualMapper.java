package com.webvisit.dao.common;

import com.webvisit.model.po.AttenceAnnual;

public interface AttenceAnnualMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceAnnual record);

    int insertSelective(AttenceAnnual record);

    AttenceAnnual selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceAnnual record);

    int updateByPrimaryKey(AttenceAnnual record);
}

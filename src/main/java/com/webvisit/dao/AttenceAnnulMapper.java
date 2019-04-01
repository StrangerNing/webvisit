package com.webvisit.dao;

import com.webvisit.model.AttenceAnnul;

public interface AttenceAnnulMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceAnnul record);

    int insertSelective(AttenceAnnul record);

    AttenceAnnul selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceAnnul record);

    int updateByPrimaryKey(AttenceAnnul record);
}
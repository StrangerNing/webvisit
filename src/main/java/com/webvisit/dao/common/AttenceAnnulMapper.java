package com.webvisit.dao.common;

import com.webvisit.model.po.AttenceAnnul;

public interface AttenceAnnulMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceAnnul record);

    int insertSelective(AttenceAnnul record);

    AttenceAnnul selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceAnnul record);

    int updateByPrimaryKey(AttenceAnnul record);
}
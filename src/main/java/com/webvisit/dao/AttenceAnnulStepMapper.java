package com.webvisit.dao;

import com.webvisit.model.AttenceAnnulStep;

public interface AttenceAnnulStepMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceAnnulStep record);

    int insertSelective(AttenceAnnulStep record);

    AttenceAnnulStep selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceAnnulStep record);

    int updateByPrimaryKey(AttenceAnnulStep record);
}
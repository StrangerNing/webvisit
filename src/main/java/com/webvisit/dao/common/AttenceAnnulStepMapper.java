package com.webvisit.dao.common;

import com.webvisit.model.po.AttenceAnnulStep;

public interface AttenceAnnulStepMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceAnnulStep record);

    int insertSelective(AttenceAnnulStep record);

    AttenceAnnulStep selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceAnnulStep record);

    int updateByPrimaryKey(AttenceAnnulStep record);
}
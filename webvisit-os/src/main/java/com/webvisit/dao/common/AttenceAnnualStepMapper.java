package com.webvisit.dao.common;

import com.webvisit.model.po.AttenceAnnualStep;

public interface AttenceAnnualStepMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceAnnualStep record);

    int insertSelective(AttenceAnnualStep record);

    AttenceAnnualStep selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceAnnualStep record);

    int updateByPrimaryKey(AttenceAnnualStep record);
}

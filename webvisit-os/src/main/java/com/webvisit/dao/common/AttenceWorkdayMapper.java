package com.webvisit.dao.common;

import com.webvisit.model.po.AttenceWorkday;

public interface AttenceWorkdayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceWorkday record);

    int insertSelective(AttenceWorkday record);

    AttenceWorkday selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceWorkday record);

    int updateByPrimaryKey(AttenceWorkday record);
}
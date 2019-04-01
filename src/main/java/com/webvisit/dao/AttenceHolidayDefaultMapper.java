package com.webvisit.dao;

import com.webvisit.model.AttenceHolidayDefault;

public interface AttenceHolidayDefaultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceHolidayDefault record);

    int insertSelective(AttenceHolidayDefault record);

    AttenceHolidayDefault selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceHolidayDefault record);

    int updateByPrimaryKey(AttenceHolidayDefault record);
}
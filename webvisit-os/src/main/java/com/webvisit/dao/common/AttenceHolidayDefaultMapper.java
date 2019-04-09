package com.webvisit.dao.common;

import com.webvisit.model.po.AttenceHolidayDefault;

public interface AttenceHolidayDefaultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceHolidayDefault record);

    int insertSelective(AttenceHolidayDefault record);

    AttenceHolidayDefault selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceHolidayDefault record);

    int updateByPrimaryKey(AttenceHolidayDefault record);
}
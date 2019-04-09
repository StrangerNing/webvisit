package com.webvisit.dao.common;

import com.webvisit.model.po.AttenceHolidayCustom;

public interface AttenceHolidayCustomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceHolidayCustom record);

    int insertSelective(AttenceHolidayCustom record);

    AttenceHolidayCustom selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceHolidayCustom record);

    int updateByPrimaryKey(AttenceHolidayCustom record);
}
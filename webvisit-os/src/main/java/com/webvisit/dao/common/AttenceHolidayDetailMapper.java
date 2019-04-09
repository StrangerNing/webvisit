package com.webvisit.dao.common;

import com.webvisit.model.po.AttenceHolidayDetail;

public interface AttenceHolidayDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceHolidayDetail record);

    int insertSelective(AttenceHolidayDetail record);

    AttenceHolidayDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceHolidayDetail record);

    int updateByPrimaryKey(AttenceHolidayDetail record);
}
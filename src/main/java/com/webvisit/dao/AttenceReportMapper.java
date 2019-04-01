package com.webvisit.dao;

import com.webvisit.model.AttenceReport;

public interface AttenceReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceReport record);

    int insertSelective(AttenceReport record);

    AttenceReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceReport record);

    int updateByPrimaryKey(AttenceReport record);
}
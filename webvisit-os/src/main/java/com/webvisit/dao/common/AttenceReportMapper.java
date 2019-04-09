package com.webvisit.dao.common;

import com.webvisit.model.po.AttenceReport;

public interface AttenceReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceReport record);

    int insertSelective(AttenceReport record);

    AttenceReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceReport record);

    int updateByPrimaryKey(AttenceReport record);
}
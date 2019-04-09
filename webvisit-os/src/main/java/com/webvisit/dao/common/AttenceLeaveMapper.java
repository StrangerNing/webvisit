package com.webvisit.dao.common;

import com.webvisit.model.po.AttenceLeave;

public interface AttenceLeaveMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceLeave record);

    int insertSelective(AttenceLeave record);

    AttenceLeave selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceLeave record);

    int updateByPrimaryKey(AttenceLeave record);
}
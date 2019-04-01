package com.webvisit.dao;

import com.webvisit.model.AttenceRecord;

public interface AttenceRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceRecord record);

    int insertSelective(AttenceRecord record);

    AttenceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceRecord record);

    int updateByPrimaryKey(AttenceRecord record);
}
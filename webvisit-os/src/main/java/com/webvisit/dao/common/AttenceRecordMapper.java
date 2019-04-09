package com.webvisit.dao.common;

import com.webvisit.model.po.AttenceRecord;

public interface AttenceRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceRecord record);

    int insertSelective(AttenceRecord record);

    AttenceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceRecord record);

    int updateByPrimaryKey(AttenceRecord record);
}
package com.webvisit.dao;

import com.webvisit.model.po.AttenceLeave;

import java.util.List;

/**
 * @author zening.zhu
 */
public interface AttenceLeaveExtMapper {

    /**
     * 根据条件查询请假类型列表
     * @param attenceLeave 查询参数
     * @return 请假类型列表
     */
    List<AttenceLeave> selectByCondition(AttenceLeave attenceLeave);
}

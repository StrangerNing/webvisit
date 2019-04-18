package com.webvisit.dao;

import com.webvisit.model.po.AttenceHolidayDetail;

import java.util.List;

/**
 * @author Tsening Chu
 */
public interface AttenceHolidayDetailExtMapper {
    /**
     * 根据员工id获取假期详情
     * @param empId 员工id
     * @return 假期详情
     */
    List<AttenceHolidayDetail> selectByEmpId(Long empId);
}

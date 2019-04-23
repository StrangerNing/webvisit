package com.webvisit.dao;

import com.webvisit.model.vo.AttenceReportVO;

import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/8
 */

public interface AttenceReportExtMapper {

    /**
     * 根据公司id获取考勤报告
     * @param attenceReportVO 查询条件
     * @return AttenceReportVO
     */
    List<AttenceReportVO> selectByCondition(AttenceReportVO attenceReportVO);
}

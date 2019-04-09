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
     * @param companyId 公司id
     * @return AttenceReportVO
     */
    List<AttenceReportVO> selectByCompanyId(Long companyId);
}

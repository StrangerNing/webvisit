package com.webvisit.dao;

import com.webvisit.model.po.AttenceHolidayCustom;

import java.util.Date;
import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/4
 */

public interface AttenceHolidayCustomExtMapper {
    /**
     * 根据日期查询自定义节假日
     * @param companyId 公司id
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 自定义节假日
     */
    List<AttenceHolidayCustom> selectByDate(Long companyId, Date beginDate, Date endDate);
}

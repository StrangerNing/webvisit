package com.webvisit.dao;

import com.webvisit.model.po.AttenceHolidayDefault;

import java.util.Date;
import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/4
 */

public interface AttenceHolidayDefaultExtMapper {

    /**
     * 根据时间查询节假日
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 节假日列表
     */
    List<AttenceHolidayDefault> selectByDate(Date beginDate,Date endDate);

}

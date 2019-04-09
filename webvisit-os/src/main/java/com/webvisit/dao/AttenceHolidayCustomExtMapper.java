package com.webvisit.dao;

import com.webvisit.model.po.AttenceHolidayCustom;
import com.webvisit.model.vo.HolidayVO;

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
    List<HolidayVO> selectByDate(Long companyId, Date beginDate, Date endDate);

    /**
     * 根据公司id和节假日日期更新自定义节假日
     * @param attenceHolidayCustom 节假日信息
     * @return 影响条数
     */
    int updateByDate(AttenceHolidayCustom attenceHolidayCustom);

    /**
     * 根据公司id和节假日日期删除自定义节假日
     * @param attenceHolidayCustom 节假日信息
     * @return 影响条数
     */
    int deleteByDate(AttenceHolidayCustom attenceHolidayCustom);
}

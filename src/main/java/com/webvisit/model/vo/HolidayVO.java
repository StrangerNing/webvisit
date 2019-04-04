package com.webvisit.model.vo;

import com.webvisit.model.po.AttenceHolidayCustom;
import com.webvisit.model.po.AttenceHolidayDefault;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/4
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HolidayVO {

    private Date holidayDate;

    private Integer defaultType;

    private Integer customType;

}

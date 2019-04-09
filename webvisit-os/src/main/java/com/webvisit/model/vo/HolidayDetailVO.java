package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/8
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HolidayDetailVO {

    @NotNull(message = "详情id不可为空")
    private Long id;

    private Long empId;

    private Integer leaveType;

    private Long leaveId;

    private Integer thisYearLeaveLeft;

    private Integer lastYearLeaveLeft;

    private Integer vacationNumber;
}

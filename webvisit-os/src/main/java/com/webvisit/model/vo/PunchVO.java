package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/28
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PunchVO {

    @NotNull(message = "职工ID不能为空")
    private Long empId;

    @NotNull(message = "打卡地点不能为空")
    private BigDecimal punchLocationLat;

    @NotNull(message = "打卡地点不能为空")
    private BigDecimal punchLocationLng;

}

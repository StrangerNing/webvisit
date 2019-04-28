package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Long empId;

    private BigDecimal punchLocationLat;

    private BigDecimal punchLocationLng;

}

package com.webvisit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/11
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegulationDTO {

    private Long id;

    private Long companyId;

    private String name;

    private String punchInStart;

    private String punchInEnd;

    private String punchOutStart;

    private String punchOutEnd;

    private String allowLate;

    private String allowLeaveEarly;

    private Integer allowLocationOffset;

    private BigDecimal checkLocationLon;

    private BigDecimal checkLocationLat;

    private Integer type;

    private Date createTime;

    private Long createAccountId;

    private Date modifyTime;

    private Long modifyAccountId;

    private String remark;
}

package com.webvisit.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/11
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegulationVO {

    private Long id;

    private Long companyId;

    private String name;

    @JsonFormat(pattern = "HH:mm:ss",timezone="GMT+8")
    private Date punchInStart;

    @JsonFormat(pattern = "HH:mm:ss",timezone="GMT+8")
    private Date punchInEnd;

    @JsonFormat(pattern = "HH:mm:ss",timezone="GMT+8")
    private Date punchOutStart;

    @JsonFormat(pattern = "HH:mm:ss",timezone="GMT+8")
    private Date punchOutEnd;

    @JsonFormat(pattern = "HH:mm:ss",timezone="GMT+8")
    private Date allowLate;

    @JsonFormat(pattern = "HH:mm:ss",timezone="GMT+8")
    private Date allowLeaveEarly;

    private Integer allowLocationOffset;

    private BigDecimal checkLocationLon;

    private BigDecimal checkLocationLat;

    private Integer type;
}

package com.webvisit.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webvisit.common.component.CommonQueryParam;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/8
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PunchDetailVO extends CommonQueryParam {

    private String nickname;

    private String deptName;

    private Long deptId;

    private Long companyId;

    private Long id;

    private Long empId;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date punchTime;

    @JsonFormat(pattern = "HH:mm:ss",timezone="GMT+8")
    private Date punchInTime;

    @JsonFormat(pattern = "HH:mm:ss",timezone="GMT+8")
    private Date punchOutTime;

    private Integer punchInStatus;

    private Integer punchOutStatus;

    private BigDecimal punchInLocationLon;

    private BigDecimal punchInLocationLat;

    private BigDecimal punchOutLocationLon;

    private BigDecimal punchOutLocationLat;

    private Integer punchType;

    private Long leaveId;

    private String punchInLocation;

    private String punchOutLocation;
}

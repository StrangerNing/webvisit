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
 * @date 2019/4/24
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PunchDetailExportVO {

    private String index;

    private String nickname;

    private String deptName;

    private String punchTime;

    private String punchInTime;

    private String punchOutTime;

    private String punchInStatus;

    private String punchOutStatus;

    private String punchInLocationLon;

    private String punchInLocationLat;

    private String punchOutLocationLon;

    private String punchOutLocationLat;

    private String punchType;

    private String leaveId;
}

package com.webvisit.model.vo;

import com.webvisit.common.re.CommonQueryParam;
import com.webvisit.model.po.AttenceReport;
import lombok.*;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/8
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttenceReportVO extends CommonQueryParam {

    private Long id;

    private Long empId;

    private Integer punchInCount;

    private Integer punchInLateCount;

    private Integer punchInMissCount;

    private Integer punchOutCount;

    private Integer punchOutEarlyCount;

    private Integer punchOutMissCount;

    private Integer askLeaveCount;

    private Integer workOutsideCount;

    private String empName;

    private String deptName;

    private Long companyId;

    private Long deptId;

}

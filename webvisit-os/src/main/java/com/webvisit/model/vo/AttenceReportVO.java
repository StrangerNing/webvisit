package com.webvisit.model.vo;

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
public class AttenceReportVO extends AttenceReport {

    private String empName;

    private String deptName;

    private Long companyId;

    private Long deptId;

}

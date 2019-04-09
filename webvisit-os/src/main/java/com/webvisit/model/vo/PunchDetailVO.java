package com.webvisit.model.vo;

import com.webvisit.model.po.AttencePunchDetail;
import lombok.*;

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
public class PunchDetailVO extends AttencePunchDetail {

    private String nickname;

    private String deptName;

    private Long deptId;

    private Long companyId;

    private Date beginDate;

    private Date endDate;
}

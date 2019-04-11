package com.webvisit.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date beginDate;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date endDate;
}

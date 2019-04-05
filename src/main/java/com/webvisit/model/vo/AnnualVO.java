package com.webvisit.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/6
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnualVO {

    private Long id;

    private Long companyId;

    @NotNull(message = "上年年假到期日不可为空！")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date expireDate;

    @NotNull(message = "请设置年假是否累积到下一年")
    private Integer accumulateToNextYear;

    @NotNull(message = "请设置试用期是否享受年假")
    private Integer probationHas;

    @NotNull(message = "请设置毕业未满一年是否享受年假")
    private Integer graduationOneYearHas;

    @NotNull(message = "请设置启用状态")
    private Integer status;

    private Date createTime;

    private Long createAccountId;

    private Date modifyTime;

    private Long modifyAccountId;

    private String remark;
}

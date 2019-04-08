package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/8
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveVO {
    private Long id;

    private Long companyId;

    @NotBlank(message = "请输入请假类型名")
    private String name;

    private Integer type;

    @NotBlank(message = "请输入可请假天数")
    private Integer availableDays;

    @NotNull(message = "请输入薪资比例")
    private BigDecimal salaryPercent;
}

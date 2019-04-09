package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/6
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnualStepVO {

    private Long id;

    @NotNull(message = "年假规则id不可为空，请重试")
    private Long annualId;

    @NotNull(message = "参数须填全！")
    private Integer moreThan;

    @NotNull(message = "参数须填全！")
    private Integer lessThan;

    @NotNull(message = "参数须填全！")
    private Integer vacationDays;
}

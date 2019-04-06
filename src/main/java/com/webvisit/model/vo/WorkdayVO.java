package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/6
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkdayVO {

    @NotEmpty(message = "至少选择一天工作日")
    private List<Integer> workdayList;

    @NotNull(message = "考勤规则id缺失！")
    private Long regulationId;
}

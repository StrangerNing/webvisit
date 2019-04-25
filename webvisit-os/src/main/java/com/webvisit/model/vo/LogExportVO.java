package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/25
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogExportVO {
    private String index;

    private String username;

    private String operation;

    private String method;

    private String params;

    private String ip;

    private String createTime;
}

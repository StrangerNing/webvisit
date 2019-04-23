package com.webvisit.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webvisit.common.re.CommonQueryParam;
import lombok.*;

import java.util.Date;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogVO extends CommonQueryParam {
    private Long id;

    private String username;

    private String operation;

    private String method;

    private String params;

    private String ip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Long companyId;
}

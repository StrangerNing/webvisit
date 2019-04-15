package com.webvisit.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogVO {
    private Long id;

    private String username;

    private String operation;

    private String method;

    private String params;

    private String ip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private Long companyId;
}

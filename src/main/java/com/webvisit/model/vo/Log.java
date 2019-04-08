package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/8
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Log implements Serializable {

    private Long id;

    private String username;

    private String operation;

    private String method;

    private String params;

    private String ip;

    private Date createTime;
}

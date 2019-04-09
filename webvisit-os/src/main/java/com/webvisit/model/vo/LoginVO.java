package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/3
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LoginVO {

    private String username;

    private String password;
}

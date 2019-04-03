package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/3
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVO {

    private String username;

    private String password;

    private String checkPassword;
}

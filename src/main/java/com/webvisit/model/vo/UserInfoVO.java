package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zening.zhu@ucarinc.com
 * @version 1.0
 * @date 2019/4/3
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {

    private Long id;

    private String username;

    private String nickname;

    private Boolean sex;

    private String email;

    private String mobile;

    private Long companyId;

    private String companyName;

    private Long deptId;

    private String deptName;

    private Date employmentDate;

    private Boolean status;

    private Date lastTime;
}

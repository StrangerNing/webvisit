package com.webvisit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UserSimpleDTO {
    private Long id;

    private String username;

    private String nickname;

    private Boolean sex;

    private String email;

    private String mobile;

    private String companyName;

    private String deptName;

    private Date employmentDate;
}

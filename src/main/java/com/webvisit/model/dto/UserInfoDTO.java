package com.webvisit.model.dto;

import com.webvisit.model.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/3
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO extends User {

    private String CompanyName;

    private String DeptName;
}

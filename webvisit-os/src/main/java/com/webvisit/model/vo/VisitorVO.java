package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/6/3
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitorVO {
    private Long id;

    private Long companyId;

    private String nickname;

    private Long empId;

    private String name;

    private String faceInfo;

    private String idCard;

    private String businessCard;
}

package com.webvisit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/18
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImgVO {

    private Long id;

    @NotNull(message = "产品id不能为空")
    private Long productId;

    @NotBlank(message = "图片链接不能为空")
    private String imgUrl;

    @NotBlank(message = "图片详情不能为空")
    private String imgDetail;

    private Date createTime;
}

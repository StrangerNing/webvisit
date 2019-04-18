package com.webvisit.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/13
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyInfoVO {

    @NotNull(message = "企业id不可为空！")
    private Long id;

    private String name;

    private String scopeOfBusiness;

    private Long type;

    private String address;

    private String url;

    private String loginLogo;

    private Boolean loginLogoChanged;

    private String pageLogo;

    private Boolean pageLogoChanged;

    private String webLogo;

    private Boolean webLogoChanged;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date registTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;
}

package com.webvisit.model.vo;

import com.webvisit.model.po.CompanyProductImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/18
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {

    private Long id;

    private Long companyId;

    private String name;

    private String detail;

    private String productUrl;

    private List<CompanyProductImg> productImgList;

}

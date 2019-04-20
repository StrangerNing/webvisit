package com.webvisit.controller;

import com.github.tobato.fastdfs.domain.conn.ConnectionManager;
import com.webvisit.common.annotation.GetLog;
import com.webvisit.common.annotation.LoginUser;
import com.webvisit.common.re.Result;
import com.webvisit.model.po.CompanyProduct;
import com.webvisit.model.vo.CompanyInfoVO;
import com.webvisit.model.vo.ProductImgVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.YellowPageService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/13
 */

@RestController
@EnableAutoConfiguration
@RequestMapping("/yellowPage")
public class YellowPageController {
    @Resource
    private YellowPageService yellowPageService;

    @RequestMapping("/query")
    Result queryCompanyInfo(@LoginUser UserInfoVO userInfoVO) {
        return Result.success(yellowPageService.queryCompanyInfo(userInfoVO));
    }

    @RequestMapping("/update")
    @GetLog(value = "更新公司信息")
    Result updateCompanyInfo(@LoginUser UserInfoVO userInfoVO, @RequestBody CompanyInfoVO companyInfoVO) {
        return Result.success(yellowPageService.saveCompanyInfo(userInfoVO, companyInfoVO));
    }

    @RequestMapping("/img/save")
    Result saveCompanyImg(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return Result.success(yellowPageService.saveCompanyImg(multipartFile));
    }

    @RequestMapping("/product/query")
    Result queryCompanyProduct(@LoginUser UserInfoVO userInfoVO) {
        return Result.success(yellowPageService.queryCompanyProduct(userInfoVO));
    }

    @RequestMapping("/product/add")
    @GetLog(value = "增加公司产品")
    Result addCompanyProduct(@LoginUser UserInfoVO userInfoVO, @RequestBody CompanyProduct companyProduct) {
        return Result.success(yellowPageService.addCompanyProduct(userInfoVO, companyProduct));
    }

    @RequestMapping("/product/update")
    @GetLog(value = "编辑公司产品")
    Result editCompanyProduct(@LoginUser UserInfoVO userInfoVO, @RequestBody CompanyProduct companyProduct) {
        return Result.success(yellowPageService.editCompanyProduct(userInfoVO, companyProduct));
    }

    @RequestMapping("/product/delete")
    @GetLog(value = "删除公司产品")
    Result deleteCompanyProduct(@LoginUser UserInfoVO userInfoVO, Long productId) {
        return Result.success(yellowPageService.deleteCompanyProduct(userInfoVO, productId));
    }

    @RequestMapping("/product/img/add")
    @GetLog(value = "增加公司产品图片")
    Result addCompanyProductImg(@LoginUser UserInfoVO userInfoVO, @RequestBody ProductImgVO productImgVO) {
        return Result.success(yellowPageService.addProductImg(userInfoVO, productImgVO));
    }

    @RequestMapping("/product/img/update")
    @GetLog(value = "修改公司产品图片")
    Result editCompanyProductImg(@LoginUser UserInfoVO userInfoVO, @RequestBody ProductImgVO productImgVO) {
        return Result.success(yellowPageService.editProductImg(userInfoVO, productImgVO));
    }

    @RequestMapping("/product/img/delete")
    @GetLog(value = "删除公司产品图片")
    Result deleteCompanyProductImg(@LoginUser UserInfoVO userInfoVO, Long id) {
        return Result.success(yellowPageService.deleteProductImg(userInfoVO, id));
    }

}

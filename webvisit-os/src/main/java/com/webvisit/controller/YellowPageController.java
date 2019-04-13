package com.webvisit.controller;

import com.webvisit.common.annotation.GetLog;
import com.webvisit.common.annotation.LoginUser;
import com.webvisit.common.re.Result;
import com.webvisit.model.vo.CompanyInfoVO;
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
}

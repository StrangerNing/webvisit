package com.webvisit.service;

import com.webvisit.model.po.CompanyInfo;
import com.webvisit.model.vo.CompanyInfoVO;
import com.webvisit.model.vo.UserInfoVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/13
 */
public interface YellowPageService {

    /**
     * 保存企业信息
     * @param userInfoVO 当前用户信息
     * @param companyInfoVO 企业信息
     * @return 保存结果
     */
    Boolean saveCompanyInfo(UserInfoVO userInfoVO, CompanyInfoVO companyInfoVO);

    /**
     * 查询企业信息
     * @param userInfoVO 当前用户信息
     * @return 企业信息
     */
    CompanyInfoVO queryCompanyInfo(UserInfoVO userInfoVO);

    String saveCompanyImg(MultipartFile multipartFile) throws IOException;

}

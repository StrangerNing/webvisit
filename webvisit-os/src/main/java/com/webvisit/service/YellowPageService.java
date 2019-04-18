package com.webvisit.service;

import com.webvisit.model.po.CompanyInfo;
import com.webvisit.model.po.CompanyProduct;
import com.webvisit.model.po.CompanyProductImg;
import com.webvisit.model.vo.CompanyInfoVO;
import com.webvisit.model.vo.ProductImgVO;
import com.webvisit.model.vo.ProductVO;
import com.webvisit.model.vo.UserInfoVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/13
 */
public interface YellowPageService {

    /**
     * 保存企业信息
     *
     * @param userInfoVO    当前用户信息
     * @param companyInfoVO 企业信息
     * @return 保存结果
     */
    Boolean saveCompanyInfo(UserInfoVO userInfoVO, CompanyInfoVO companyInfoVO);

    /**
     * 查询企业信息
     *
     * @param userInfoVO 当前用户信息
     * @return 企业信息
     */
    CompanyInfoVO queryCompanyInfo(UserInfoVO userInfoVO);

    /**
     * 上传图片返回图片地址
     *
     * @param multipartFile 文件
     * @return 图片地址
     * @throws IOException 异常
     */
    String saveCompanyImg(MultipartFile multipartFile) throws IOException;

    /**
     * 查询公司产品
     *
     * @param userInfoVO 当前用户信息
     * @return 产品列表
     */
    List<ProductVO> queryCompanyProduct(UserInfoVO userInfoVO);

    /**
     * 添加公司产品
     *
     * @param companyProduct 产品信息
     * @param userInfoVO     当前用户信息
     * @return 添加结果
     */
    Boolean addCompanyProduct(UserInfoVO userInfoVO, CompanyProduct companyProduct);

    /**
     * 编辑公司产品
     *
     * @param companyProduct 产品信息
     * @param userInfoVO     当前用户信息
     * @return 编辑结果
     */
    Boolean editCompanyProduct(UserInfoVO userInfoVO, CompanyProduct companyProduct);

    /**
     * 删除公司产品
     * @param userInfoVO 当前用户信息
     * @param productId 产品id
     * @return 删除结果
     */
    Boolean deleteCompanyProduct(UserInfoVO userInfoVO,Long productId);

    /**
     * 添加公司产品图片
     * @param userInfoVO 当前用户信息
     * @param productImgVO 产品图片
     * @return 添加结果
     */
    Boolean addProductImg(UserInfoVO userInfoVO, ProductImgVO productImgVO);

    /**
     * 修改公司产品图片
     * @param userInfoVO 当前用户信息
     * @param productImgVO 产品图片
     * @return 修改结果
     */
    Boolean editProductImg(UserInfoVO userInfoVO, ProductImgVO productImgVO);

    /**
     * 删除公司产品图片
     * @param userInfoVO 当前用户信息
     * @param id 图片id
     * @return 删除结果
     */
    Boolean deleteProductImg(UserInfoVO userInfoVO, Long id);

}

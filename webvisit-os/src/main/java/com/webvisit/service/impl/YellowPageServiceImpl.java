package com.webvisit.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.webvisit.common.constant.LocalConstant;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.dao.CompanyInfoExtMapper;
import com.webvisit.dao.CompanyProductExtMapper;
import com.webvisit.dao.CompanyProductImgExtMapper;
import com.webvisit.dao.common.CompanyInfoMapper;
import com.webvisit.dao.common.CompanyProductImgMapper;
import com.webvisit.dao.common.CompanyProductMapper;
import com.webvisit.model.po.CompanyInfo;
import com.webvisit.model.po.CompanyProduct;
import com.webvisit.model.po.CompanyProductImg;
import com.webvisit.model.vo.CompanyInfoVO;
import com.webvisit.model.vo.ProductImgVO;
import com.webvisit.model.vo.ProductVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.YellowPageService;
import com.webvisit.utils.MultipartFileUtil;
import com.webvisit.utils.TimeUtil;
import com.webvisit.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/13
 */

@Service
public class YellowPageServiceImpl implements YellowPageService {

    @Resource
    private CompanyInfoMapper companyInfoMapper;
    @Resource
    private CompanyInfoExtMapper companyInfoExtMapper;
    @Resource
    private CompanyProductMapper companyProductMapper;
    @Resource
    private CompanyProductExtMapper companyProductExtMapper;
    @Resource
    private CompanyProductImgMapper companyProductImgMapper;
    @Resource
    private CompanyProductImgExtMapper companyProductImgExtMapper;
    @Resource
    private FastFileStorageClient storageClient;

    @Override
    public Boolean saveCompanyInfo(UserInfoVO userInfoVO, CompanyInfoVO companyInfoVO) {
        ValidatorUtil.validate(companyInfoVO);
        Long companyId = companyInfoVO.getId();
        CompanyInfo queryCompany = companyInfoMapper.selectByPrimaryKey(companyId);
        if (null == queryCompany) {
            throw new BusinessException("没有找到相应的公司！");
        }
        if (!userInfoVO.getCompanyId().equals(companyId)) {
            throw new BusinessException("你没有权限进行该操作");
        }
        Boolean loginLogoChanged = companyInfoVO.getLoginLogoChanged();
        Boolean pageLogoChanged = companyInfoVO.getPageLogoChanged();
        Boolean webLogoChanged = companyInfoVO.getWebLogoChanged();
        if (loginLogoChanged != null) {
            if (!companyInfoVO.getLoginLogoChanged()) {
                companyInfoVO.setLoginLogo(null);
            }
        }
        if (pageLogoChanged != null) {
            if (!companyInfoVO.getPageLogoChanged()) {
                companyInfoVO.setPageLogo(null);
            }
        }
        if (webLogoChanged != null) {
            if (!companyInfoVO.getWebLogoChanged()) {
                companyInfoVO.setWebLogo(null);
            }
        }
        CompanyInfo companyInfo = new CompanyInfo();
        BeanUtils.copyProperties(companyInfoVO, companyInfo);
        return companyInfoMapper.updateByPrimaryKeySelective(companyInfo) == 1;
    }

    @Override
    public CompanyInfoVO queryCompanyInfo(UserInfoVO userInfoVO) {
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(userInfoVO.getCompanyId());
        CompanyInfoVO companyInfoVO = new CompanyInfoVO();
        BeanUtils.copyProperties(companyInfo, companyInfoVO);
        return companyInfoVO;
    }

    @Override
    public String saveCompanyImg(MultipartFile multipartFile) throws IOException {
        String extFilename = MultipartFileUtil.getExtFilename(multipartFile);
        StorePath path = storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), extFilename, null);
        return LocalConstant.IMG_SERVER_ADDRESS + path.getFullPath();
    }

    @Override
    public List<ProductVO> queryCompanyProduct(UserInfoVO userInfoVO) {
        CompanyProduct companyProduct = new CompanyProduct();
        companyProduct.setCompanyId(userInfoVO.getCompanyId());
        List<ProductVO> productList = companyProductExtMapper.selectByCondition(companyProduct);
        for (ProductVO product : productList) {
            Long productId = product.getId();
            CompanyProductImg companyProductImg = new CompanyProductImg();
            companyProductImg.setProductId(productId);
            List<CompanyProductImg> productImgList = companyProductImgExtMapper.selectByCondition(companyProductImg);
            product.setProductImgList(productImgList);
        }
        return productList;
    }

    @Override
    public Boolean addCompanyProduct(UserInfoVO userInfoVO, CompanyProduct companyProduct) {
        companyProduct.setCreateTime(TimeUtil.createNowTime());
        companyProduct.setCreateAccountId(userInfoVO.getId());
        companyProduct.setCompanyId(userInfoVO.getCompanyId());
        return companyProductMapper.insertSelective(companyProduct) == 1;
    }

    @Override
    public Boolean editCompanyProduct(UserInfoVO userInfoVO, CompanyProduct companyProduct) {
        Long productId = companyProduct.getId();
        if (null == productId) {
            throw new BusinessException("产品id不能为空");
        }
        CompanyProduct queryProduct = companyProductMapper.selectByPrimaryKey(productId);
        if (null == queryProduct) {
            throw new BusinessException("没有找到相关的产品");
        }
        Long companyId = queryProduct.getCompanyId();
        if (companyId == null) {
            throw new BusinessException("相关产品数据出错");
        }
        if (!companyId.equals(userInfoVO.getCompanyId())) {
            throw new BusinessException("你没有权限编辑该产品");
        }
        companyProduct.setCompanyId(companyId);
        return companyProductMapper.updateByPrimaryKeySelective(companyProduct) == 1;
    }

    @Override
    public Boolean deleteCompanyProduct(UserInfoVO userInfoVO, Long productId) {
        CompanyProduct queryProduct = companyProductMapper.selectByPrimaryKey(productId);
        Long companyId = queryProduct.getCompanyId();
        if (null == companyId) {
            throw new BusinessException("当前产品数据出错");
        }
        if (!companyId.equals(userInfoVO.getCompanyId())) {
            throw new BusinessException("你没有权限删除该产品");
        }
        CompanyProductImg companyProductImg = new CompanyProductImg();
        companyProductImg.setProductId(productId);
        companyProductImgExtMapper.deleteByCondition(companyProductImg);
        return companyProductMapper.deleteByPrimaryKey(productId) == 1;
    }

    @Override
    public Boolean addProductImg(UserInfoVO userInfoVO, ProductImgVO productImgVO) {
        checkProductImg(userInfoVO, productImgVO);
        CompanyProductImg companyProductImg = new CompanyProductImg();
        BeanUtils.copyProperties(productImgVO, companyProductImg);
        companyProductImg.setCreateTime(TimeUtil.createNowTime());
        return companyProductImgMapper.insertSelective(companyProductImg) == 1;
    }

    private void checkProductImg(UserInfoVO userInfoVO, ProductImgVO productImgVO) {
        ValidatorUtil.validate(productImgVO);
        CompanyProduct queryProduct = companyProductMapper.selectByPrimaryKey(productImgVO.getId());
        Long companyId = queryProduct.getCompanyId();
        if (companyId == null) {
            throw new BusinessException("产品信息出现错误");
        }
        if (!userInfoVO.getCompanyId().equals(companyId)) {
            throw new BusinessException("你没有权限操作这个产品图片");
        }
    }

    @Override
    public Boolean editProductImg(UserInfoVO userInfoVO, ProductImgVO productImgVO) {
        checkProductImg(userInfoVO, productImgVO);
        if (productImgVO.getId() == null) {
            throw new BusinessException("图片id不能为空");
        }
        CompanyProductImg companyProductImg = new CompanyProductImg();
        BeanUtils.copyProperties(productImgVO, companyProductImg);
        return companyProductImgMapper.updateByPrimaryKeySelective(companyProductImg) == 1;
    }

    @Override
    public Boolean deleteProductImg(UserInfoVO userInfoVO, Long id) {
        CompanyProductImg queryImg = companyProductImgMapper.selectByPrimaryKey(id);
        if (queryImg == null) {
            throw new BusinessException("没有找到这个产品图片");
        }
        Long productId = queryImg.getProductId();
        if (null == productId) {
            throw new BusinessException("产品图片数据出错");
        }
        CompanyProduct queryProduct = companyProductMapper.selectByPrimaryKey(productId);
        if (queryProduct == null) {
            throw new BusinessException("没有找到相关产品");
        }
        Long companyId = queryProduct.getCompanyId();
        if (null == companyId) {
            throw new BusinessException("相关产品数据出错");
        }
        if (!companyId.equals(userInfoVO.getCompanyId())) {
            throw new BusinessException("你没有权限删除这个产品图片");
        }
        return companyProductImgMapper.deleteByPrimaryKey(id) == 1;
    }
}

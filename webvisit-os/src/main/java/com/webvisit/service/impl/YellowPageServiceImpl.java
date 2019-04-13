package com.webvisit.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.webvisit.common.constant.LocalConstant;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.dao.CompanyInfoExtMapper;
import com.webvisit.dao.common.CompanyInfoMapper;
import com.webvisit.model.po.CompanyInfo;
import com.webvisit.model.vo.CompanyInfoVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.YellowPageService;
import com.webvisit.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

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
        CompanyInfo companyInfo = new CompanyInfo();
        BeanUtils.copyProperties(companyInfoVO, companyInfo);
        return companyInfoMapper.updateByPrimaryKeySelective(companyInfo) == 1;
    }

    @Override
    public CompanyInfoVO queryCompanyInfo(UserInfoVO userInfoVO) {
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(userInfoVO.getCompanyId());
        CompanyInfoVO companyInfoVO = new CompanyInfoVO();
        BeanUtils.copyProperties(companyInfo,companyInfoVO);
        return companyInfoVO;
    }

    @Override
    public String saveCompanyImg(MultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        if (filename != null) {
            String prefix = filename.substring(filename.lastIndexOf(".") + 1);
            StorePath path = storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), prefix, null);
            return LocalConstant.IMG_SERVER_ADDRESS+path.getFullPath();
        }
        return null;
    }
}

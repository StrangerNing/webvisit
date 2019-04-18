package com.webvisit.dao;

import com.webvisit.model.po.CompanyProductImg;

import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/18
 */

public interface CompanyProductImgExtMapper {

    /**
     * 根据查询条件查询产品图片
     *
     * @param companyProductImg 查询条件
     * @return 产品图片列表
     */
    List<CompanyProductImg> selectByCondition(CompanyProductImg companyProductImg);

    /**
     * 根据条件删除图片
     *
     * @param companyProductImg 条件
     * @return 删除条数
     */
    int deleteByCondition(CompanyProductImg companyProductImg);
}

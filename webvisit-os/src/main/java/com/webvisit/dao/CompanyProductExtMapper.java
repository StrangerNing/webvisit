package com.webvisit.dao;

import com.webvisit.model.po.CompanyProduct;
import com.webvisit.model.vo.ProductVO;

import java.util.List;

/**
 * @author Tsening Chu
 */
public interface CompanyProductExtMapper {

    /**
     * 根据条件查询产品列表
     *
     * @param product 查询条件
     * @return 产品列表
     */
    List<ProductVO> selectByCondition(CompanyProduct product);
}

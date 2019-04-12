package com.webvisit.dao;

import com.webvisit.model.po.AttenceAnnual;
import com.webvisit.model.vo.AnnualVO;

import java.util.List;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/5
 */
public interface AttenceAnnualExtMapper {

    /**
     * 根据条件查询年假规则
     *
     * @param attenceAnnual 查询条件
     * @return 年假规则列表
     */
    List<AnnualVO> selectByCondition(AttenceAnnual attenceAnnual);

    /**
     * 根据入参插入年假规则返回主键id
     *
     * @param attenceAnnual 年假规则
     * @return 影响条数
     */
    int insertSelectiveReturnId(AttenceAnnual attenceAnnual);
}

package com.webvisit.dao;

import com.webvisit.model.po.AttenceAnnualStep;

import java.util.List;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/6
 */
public interface AttenceAnnualStepExtMapper {

    /**
     * 根据年假id删除年假阶梯设置
     *
     * @param annualId 年假id
     * @return 影响行数
     */
    int deleteByAnnualId(Long annualId);

    /**
     * 根据年假id查询年假阶梯设置
     * @param annualId 年假id
     * @return 年假阶梯设置列表
     */
    List<AttenceAnnualStep> selectByAnnualId(Long annualId);
}

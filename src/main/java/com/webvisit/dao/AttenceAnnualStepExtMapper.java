package com.webvisit.dao;

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
}

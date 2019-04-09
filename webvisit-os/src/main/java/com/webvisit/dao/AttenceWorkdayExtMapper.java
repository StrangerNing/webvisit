package com.webvisit.dao;

import com.webvisit.model.po.AttenceWorkday;

import java.util.List;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/6
 */
public interface AttenceWorkdayExtMapper {

    /**
     * 根据考勤规则id查询工作日列表
     * @param regulationId 考勤规则id
     * @return 工作日列表
     */
    List<AttenceWorkday> selectByRegulationId(Long regulationId);

    /**
     * 根据考勤规则id删除工作日列表
     * @param regulationId 考勤规则id
     * @return 删除条数
     */
    int deleteByRegulationId(Long regulationId);

    /**
     * 根据考勤规则id查询工作日条数
     * @param regulationId 考勤规则id
     * @return 条数
     */
    int countByRegulationId(Long regulationId);
}

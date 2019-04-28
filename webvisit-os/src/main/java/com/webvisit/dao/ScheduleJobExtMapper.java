package com.webvisit.dao;

import com.webvisit.model.po.ScheduleJob;

import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/28
 */

public interface ScheduleJobExtMapper {

    /**
     * 查询所有定时任务
     *
     * @return 定时任务列表
     */
    List<ScheduleJob> selectAll();
}

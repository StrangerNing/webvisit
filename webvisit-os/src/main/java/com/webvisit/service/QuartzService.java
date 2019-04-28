package com.webvisit.service;

import com.webvisit.model.po.ScheduleJob;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/28
 */

public interface QuartzService {

    /**
     * 执行定时任务
     */
    void timingTask();

    /**
     * 添加定时任务
     *
     * @param job 定时任务
     */
    void addJob(ScheduleJob job);
}

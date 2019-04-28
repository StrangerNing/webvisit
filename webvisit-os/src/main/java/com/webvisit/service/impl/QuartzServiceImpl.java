package com.webvisit.service.impl;

import com.webvisit.common.enums.JobOperateEnum;
import com.webvisit.dao.ScheduleJobExtMapper;
import com.webvisit.model.po.ScheduleJob;
import com.webvisit.quartz.QuartzFactory;
import com.webvisit.service.QuartzService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/28
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class QuartzServiceImpl implements QuartzService {

    @Resource
    private Scheduler scheduler;
    @Resource
    private ScheduleJobExtMapper scheduleJobExtMapper;

    private final static Logger logger = LoggerFactory.getLogger(QuartzServiceImpl.class);

    @Override
    public void timingTask() {
        List<ScheduleJob> scheduleJobList = scheduleJobExtMapper.selectAll();
        if (scheduleJobList != null) {
            scheduleJobList.forEach(this::addJob);
        }
    }

    @Override
    public void addJob(ScheduleJob job) {
        try {
            if (JobOperateEnum.START.getValue().equals(job.getStatus())) {
                //创建触发器
                Trigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName())
                        .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                        .startNow()
                        .build();

                //创建任务
                JobDetail jobDetail = JobBuilder.newJob(QuartzFactory.class)
                        .withIdentity(job.getJobName())
                        .build();

                //传入调度的数据，在QuartzFactory中需要使用
                jobDetail.getJobDataMap().put("scheduleJob", job);

                //调度作业
                scheduler.scheduleJob(jobDetail, trigger);
                logger.info("定时任务：{} 已调度",job.getJobName());
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

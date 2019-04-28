package com.webvisit.quartz;

import com.webvisit.service.QuartzService;
import com.webvisit.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/28
 */

@Component
@Transactional(rollbackFor = Exception.class)
public class JobSchedule implements CommandLineRunner {

    @Resource
    private QuartzService quartzService;

    private final static Logger logger = LoggerFactory.getLogger(JobSchedule.class);

    @Override
    public void run(String... args) {
        Date beginTime =  TimeUtil.createNowTime();
        logger.info("任务调度开始");
        quartzService.timingTask();
        Date endTime = TimeUtil.createNowTime();
        Long timeDif = TimeUtil.dateDiff(beginTime,endTime, TimeUnit.MILLISECONDS);
        logger.info("任务调度结束，完成时间：{}ms",timeDif);
    }
}

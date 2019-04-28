package com.webvisit.quartz.job;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/28
 */

@Component("testJob01")
@Transactional(rollbackFor = Exception.class)
public class TestJob01 {

    public void execute() {
        System.out.println("测试定时任务已启动。。。。。。。。");
    }
}

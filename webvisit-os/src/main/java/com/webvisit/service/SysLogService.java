package com.webvisit.service;

import com.webvisit.model.po.Log;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/8
 */

public interface SysLogService {

    /**
     * 保存操作日志
     * @param log 日志
     * @return 保存结果
     */
    Boolean saveLog(Log log);
}

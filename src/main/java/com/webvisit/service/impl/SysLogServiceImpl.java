package com.webvisit.service.impl;

import com.webvisit.dao.common.LogMapper;
import com.webvisit.model.po.Log;
import com.webvisit.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/8
 */

@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public Boolean saveLog(Log log) {
        return logMapper.insert(log) == 1;
    }
}

package com.webvisit.dao;

import com.webvisit.model.po.Log;

import java.util.List;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/14
 */
public interface LogExtMapper {

    /**
     * 根据公司id查询操作日志
     * @param companyId 公司id
     * @return 操作日志
     */
    List<Log> queryLog(Long companyId);
}

package com.webvisit.service;

import com.webvisit.model.po.Log;
import com.webvisit.model.vo.LogVO;
import com.webvisit.model.vo.UserInfoVO;

import java.util.List;

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

    /**
     * 查看操作日志
     * @param userInfoVO 当前用户信息
     * @param logVO 查询条件
     * @return 操作日志
     */
    List<LogVO> queryLog(UserInfoVO userInfoVO, LogVO logVO);
}

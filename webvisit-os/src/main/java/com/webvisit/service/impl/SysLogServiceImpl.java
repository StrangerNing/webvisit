package com.webvisit.service.impl;

import com.webvisit.dao.LogExtMapper;
import com.webvisit.dao.common.LogMapper;
import com.webvisit.model.po.Log;
import com.webvisit.model.vo.LogVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.SysLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/8
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private LogMapper logMapper;
    @Resource
    private LogExtMapper logExtMapper;

    @Override
    public Boolean saveLog(Log log) {
        return logMapper.insertSelective(log) == 1;
    }

    @Override
    public List<LogVO> queryLog(UserInfoVO userInfoVO, LogVO logVO) {
        return logExtMapper.queryLog(userInfoVO,logVO);
    }
}

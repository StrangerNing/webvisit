package com.webvisit.controller;

import com.webvisit.common.annotation.LoginUser;
import com.webvisit.common.re.Result;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.SysLogService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/14
 */

@RestController
@EnableAutoConfiguration
@RequestMapping("log")
public class SysLogController {

    @Resource
    private SysLogService sysLogService;

    @RequestMapping("/query")
    Result queryLog(@LoginUser UserInfoVO userInfoVO) {
        return Result.success(sysLogService.queryLog(userInfoVO));
    }
}

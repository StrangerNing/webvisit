package com.webvisit.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webvisit.common.annotation.LoginUser;
import com.webvisit.common.re.Result;
import com.webvisit.model.vo.LogVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.SysLogService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    Result queryLog(@LoginUser UserInfoVO userInfoVO, LogVO logVO) {
        PageHelper.startPage(userInfoVO.getPageNum(), userInfoVO.getPageSize());
        List<LogVO> logList = sysLogService.queryLog(userInfoVO, logVO);
        PageInfo<LogVO> logPageInfo = new PageInfo<>(logList);
        return Result.success(logPageInfo);
    }

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}

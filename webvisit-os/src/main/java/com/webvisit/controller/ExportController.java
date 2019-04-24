package com.webvisit.controller;

import com.webvisit.common.annotation.LoginUser;
import com.webvisit.common.component.Result;
import com.webvisit.common.constant.LocalConstant;
import com.webvisit.model.vo.PunchDetailVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.ExportService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/24
 */

@RestController
@EnableAutoConfiguration
@EnableAsync
@RequestMapping("/export")
public class ExportController {

    @Resource
    private ExportService exportService;

    @RequestMapping("/punch/detail")
    Result exportPunchDetail(@LoginUser UserInfoVO userInfoVO, PunchDetailVO punchDetailVO, HttpServletResponse response) {
        String uuid = LocalConstant.EXPORT_UUID_KEY +UUID.randomUUID().toString();
        exportService.exportPunchDetail(userInfoVO, punchDetailVO,uuid);
        return Result.success(uuid);
    }

    @RequestMapping("/download")
    Result getDownloadUrl(String uuid) {
        String url = exportService.getDownloadUrl(uuid);
        if (url == null) {
            return Result.failure("没有处理完成或链接已过期，请重试","1");
        }
        return Result.success(exportService.getDownloadUrl(uuid));
    }

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}

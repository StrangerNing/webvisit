package com.webvisit.controller;

import com.webvisit.common.component.Result;
import com.webvisit.model.vo.PunchVO;
import com.webvisit.service.PunchService;
import com.webvisit.utils.ValidatorUtil;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/29
 */

@RestController
@EnableAsync
@RequestMapping("/punch")
public class PunchController {

    @Resource
    private PunchService punchService;

    @RequestMapping("")
    Result punch(PunchVO punchVO) {
        ValidatorUtil.validate(punchVO);
        punchService.punch(punchVO);
        return Result.success();
    }
}

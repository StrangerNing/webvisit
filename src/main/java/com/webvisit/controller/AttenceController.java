package com.webvisit.controller;

import com.webvisit.service.AttenceService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author zening.zhu@ucarinc.com
 * @version 1.0
 * @date 2019/4/1
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/attence")
public class AttenceController {

    @Resource
    private AttenceService attenceService;
}

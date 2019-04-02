package com.webvisit.controller;

import com.webvisit.common.re.Result;
import com.webvisit.model.po.AttenceRegulation;
import com.webvisit.service.AttenceService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/1
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/attence")
public class AttenceController {

    @Resource
    private AttenceService attenceService;

    @RequestMapping("/new")
    @ResponseBody
    Result addRegulation(AttenceRegulation attenceRegulation){
        return Result.success(attenceService.addRegulation(attenceRegulation));
    }

    @RequestMapping("/query")
    @ResponseBody
    Result<List<AttenceRegulation>> queryRegulations(Long companyId){
        return Result.success(attenceService.queryRequlations(companyId));
    }

}

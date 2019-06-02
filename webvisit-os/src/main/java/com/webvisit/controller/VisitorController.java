package com.webvisit.controller;

import com.webvisit.common.annotation.LoginUser;
import com.webvisit.common.component.Result;
import com.webvisit.model.po.Visitor;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.model.vo.VisitorVO;
import com.webvisit.service.VisitorService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/5/30
 */

@RestController
@EnableAutoConfiguration
@RequestMapping("/visitor")
public class VisitorController {
    @Resource
    private VisitorService visitorService;

    @RequestMapping("/query")
    Result queryVisitorList(@LoginUser UserInfoVO userInfoVO, VisitorVO visitor) {
        visitor.setCompanyId(userInfoVO.getCompanyId());
        return Result.success(visitorService.queryVisitorList(visitor));
    }

    @RequestMapping("/delete")
    Result deleteVisitor(@LoginUser UserInfoVO userInfoVO, Long id) {
        return Result.success(visitorService.deleteVisitor(userInfoVO,id));
    }
}

package com.webvisit.controller;

import com.webvisit.common.annotation.LoginUser;
import com.webvisit.common.constant.LocalConstant;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.common.re.Result;
import com.webvisit.model.po.AttenceRegulation;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.AttenceService;
import com.webvisit.utils.TimeUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
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

    @RequestMapping("/regulation/new")
    @ResponseBody
    Result addRegulation(AttenceRegulation attenceRegulation, @LoginUser UserInfoVO userInfoVO) {
        if (null != userInfoVO) {
            attenceRegulation.setCompanyId(userInfoVO.getCompanyId());
            attenceRegulation.setCreateAccountId(userInfoVO.getId());
        }
        attenceRegulation.setCreateTime(TimeUtil.createNowTime());
        return Result.success(attenceService.addRegulation(attenceRegulation));
    }

    @RequestMapping("/regulation/query")
    @ResponseBody
    Result<List<AttenceRegulation>> queryRegulations(@LoginUser UserInfoVO userInfoVO) {
        Long companyId = userInfoVO.getCompanyId();
        return Result.success(attenceService.queryRequlations(companyId));
    }

    @RequestMapping("/regulation/delete")
    @ResponseBody
    Result deleteRegulation(@LoginUser UserInfoVO userInfoVO, Long regulationId) {
        return Result.success(attenceService.delRegulation(userInfoVO,regulationId));
    }

    @RequestMapping("/regulation/update")
    @ResponseBody
    Result updateRegulation(@LoginUser UserInfoVO userInfoVO, AttenceRegulation attenceRegulation){
        return Result.success(attenceService.editRegulation(userInfoVO,attenceRegulation));
    }

}

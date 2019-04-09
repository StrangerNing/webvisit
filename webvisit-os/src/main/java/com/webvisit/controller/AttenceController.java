package com.webvisit.controller;

import com.webvisit.common.annotation.LoginUser;
import com.webvisit.common.re.Result;
import com.webvisit.model.po.AttenceRegulation;
import com.webvisit.model.vo.AnnualVO;
import com.webvisit.model.vo.LeaveVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.AttenceService;
import com.webvisit.utils.TimeUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
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
        return Result.success(attenceService.queryRegulations(companyId));
    }

    @RequestMapping("/regulation/delete")
    @ResponseBody
    Result deleteRegulation(@LoginUser UserInfoVO userInfoVO, Long regulationId) {
        return Result.success(attenceService.delRegulation(userInfoVO, regulationId));
    }

    @RequestMapping("/regulation/update")
    @ResponseBody
    Result updateRegulation(@LoginUser UserInfoVO userInfoVO, AttenceRegulation attenceRegulation) {
        return Result.success(attenceService.editRegulation(userInfoVO, attenceRegulation));
    }


    @RequestMapping("/holiday/query")
    @ResponseBody
    Result queryHolidays(@LoginUser UserInfoVO userInfoVO, Date beginDate, Date endDate) {
        return Result.success(attenceService.queryHolidays(userInfoVO, beginDate, endDate));
    }

    @RequestMapping("/holiday/new")
    @ResponseBody
    Result addHoliday(@LoginUser UserInfoVO userInfoVO, Date date) {
        return Result.success(attenceService.setHoliday(userInfoVO, date));
    }

    @RequestMapping("/holiday/cancel")
    @ResponseBody
    Result cancelHoliday(@LoginUser UserInfoVO userInfoVO, Date date) {
        return Result.success(attenceService.cancelHoliday(userInfoVO, date));
    }

    @RequestMapping("/leave/query")
    @ResponseBody
    Result queryLeave(@LoginUser UserInfoVO userInfoVO) {
        return Result.success(attenceService.queryLeave(userInfoVO));
    }

    @RequestMapping("/leave/add")
    @ResponseBody
    Result addLeave(@LoginUser UserInfoVO userInfoVO, LeaveVO leaveVO) {
        return Result.success(attenceService.addLeave(userInfoVO, leaveVO));
    }

    @RequestMapping("/leave/delete")
    @ResponseBody
    Result deleteLeave(@LoginUser UserInfoVO userInfoVO, Long leaveId) {
        return Result.success(attenceService.deleteLeave(userInfoVO, leaveId));
    }

    @RequestMapping("/annual/query")
    @ResponseBody
    Result queryAnnual(@LoginUser UserInfoVO userInfoVO) {
        return Result.success(attenceService.queryAnnul(userInfoVO));
    }

    @RequestMapping("/annual/delete")
    @ResponseBody
    Result deleteAnnual(@LoginUser UserInfoVO userInfoVO, Long annualId) {
        return Result.success(attenceService.deleteAnnul(userInfoVO, annualId));
    }

    @RequestMapping("/annual/add")
    @ResponseBody
    Result addAnnual(@LoginUser UserInfoVO userInfoVO, AnnualVO annualVO) {
        return Result.success(attenceService.addAnnual(userInfoVO, annualVO));
    }

    @RequestMapping("/annual/update")
    @ResponseBody
    Result editAnnual(@LoginUser UserInfoVO userInfoVO, AnnualVO annualVO) {
        return Result.success(attenceService.editAnnual(userInfoVO, annualVO));
    }

}

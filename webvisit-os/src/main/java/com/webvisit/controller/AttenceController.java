package com.webvisit.controller;

import com.webvisit.common.annotation.LoginUser;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.common.re.Result;
import com.webvisit.model.dto.RegulationDTO;
import com.webvisit.model.po.AttenceRegulation;
import com.webvisit.model.vo.*;
import com.webvisit.service.AttenceService;
import com.webvisit.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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

    @RequestMapping(value = "/regulation/new", method = RequestMethod.POST)
    @ResponseBody
    Result addRegulation(RegulationDTO regulationDTO, @LoginUser UserInfoVO userInfoVO) {
        if (null != userInfoVO) {
            regulationDTO.setCompanyId(userInfoVO.getCompanyId());
            regulationDTO.setCreateAccountId(userInfoVO.getId());
        }
        regulationDTO.setCreateTime(TimeUtil.createNowTime());
        return Result.success(attenceService.addRegulation(regulationDTO));
    }

    @RequestMapping("/regulation/query")
    @ResponseBody
    Result<List<RegulationVO>> queryRegulations(@LoginUser UserInfoVO userInfoVO) {
        Long companyId = userInfoVO.getCompanyId();
        return Result.success(attenceService.queryRegulations(companyId));
    }

    @RequestMapping("/regulation/delete")
    @ResponseBody
    Result deleteRegulation(@LoginUser UserInfoVO userInfoVO, Long regulationId) {
        return Result.success(attenceService.delRegulation(userInfoVO, regulationId));
    }

    @RequestMapping(value = "/regulation/update",method = RequestMethod.POST)
    @ResponseBody
    Result updateRegulation(@LoginUser UserInfoVO userInfoVO, RegulationDTO regulationDTO) {
        return Result.success(attenceService.editRegulation(userInfoVO, regulationDTO));
    }


    @RequestMapping("/holiday/query")
    @ResponseBody
    Result queryHolidays(@LoginUser UserInfoVO userInfoVO, String beginDate, String endDate) {
        Date beginTime = new Date(Long.parseLong(beginDate));
        Date endTime = new Date(Long.parseLong(endDate));
        return Result.success(attenceService.queryHolidays(userInfoVO, beginTime, endTime));
    }

    @RequestMapping(value = "/holiday/new", method = RequestMethod.POST)
    @ResponseBody
    Result addHoliday(@LoginUser UserInfoVO userInfoVO, @RequestParam Date date) {
        if (date != null) {
            return Result.success(attenceService.setHoliday(userInfoVO, date));
        } else {
            throw new BusinessException("假期日期不能为空");
        }
    }

    @RequestMapping(value = "/holiday/cancel", method = RequestMethod.POST)
    @ResponseBody
    Result cancelHoliday(@LoginUser UserInfoVO userInfoVO, @RequestParam Date date) {
        if (date != null) {
            return Result.success(attenceService.cancelHoliday(userInfoVO, date));
        } else {
            throw new BusinessException("取消日期不能为空");
        }
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

    @RequestMapping("/workday/query")
    @ResponseBody
    Result queryWorkday(@LoginUser UserInfoVO userInfoVO, Long regulationId) {
        return Result.success(attenceService.queryWorkDays(userInfoVO,regulationId));
    }

    @RequestMapping("/workday/update")
    @ResponseBody
    Result editWorkday(@LoginUser UserInfoVO userInfoVO, WorkdayVO workdayVO){
        return Result.success(attenceService.setWorkday(userInfoVO,workdayVO));
    }

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}

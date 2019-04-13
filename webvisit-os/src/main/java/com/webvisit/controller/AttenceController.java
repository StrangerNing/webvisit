package com.webvisit.controller;

import com.webvisit.common.annotation.GetLog;
import com.webvisit.common.annotation.LoginUser;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.common.re.Result;
import com.webvisit.model.dto.RegulationDTO;
import com.webvisit.model.vo.*;
import com.webvisit.service.AttenceService;
import com.webvisit.utils.TimeUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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

    @RequestMapping(value = "/regulation/new", method = RequestMethod.POST)
    @ResponseBody
    @GetLog(value = "新增考勤规则")
    Result addRegulation(@RequestBody RegulationDTO regulationDTO, @LoginUser UserInfoVO userInfoVO) {
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
    @GetLog(value = "删除考勤规则")
    Result deleteRegulation(@LoginUser UserInfoVO userInfoVO, Long regulationId) {
        return Result.success(attenceService.delRegulation(userInfoVO, regulationId));
    }

    @RequestMapping(value = "/regulation/update", method = RequestMethod.POST)
    @ResponseBody
    @GetLog(value = "更新考勤规则")
    Result updateRegulation(@LoginUser UserInfoVO userInfoVO, @RequestBody RegulationDTO regulationDTO) {
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
    @GetLog(value = "新增节假日")
    Result addHoliday(@LoginUser UserInfoVO userInfoVO, @RequestParam Date date) {
        if (date != null) {
            return Result.success(attenceService.setHoliday(userInfoVO, date));
        } else {
            throw new BusinessException("假期日期不能为空");
        }
    }

    @RequestMapping(value = "/holiday/cancel", method = RequestMethod.POST)
    @ResponseBody
    @GetLog(value = "取消节假日")
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
    @GetLog(value = "新增请假类型")
    Result addLeave(@LoginUser UserInfoVO userInfoVO, @RequestBody LeaveVO leaveVO) {
        return Result.success(attenceService.addLeave(userInfoVO, leaveVO));
    }

    @RequestMapping("/leave/update")
    @ResponseBody
    @GetLog(value = "更新请假类型")
    Result editLeave(@LoginUser UserInfoVO userInfoVO, @RequestBody LeaveVO leaveVO) {
        return Result.success(attenceService.editLeave(userInfoVO, leaveVO));
    }

    @RequestMapping("/leave/delete")
    @ResponseBody
    @GetLog(value = "删除请假类型")
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
    @GetLog(value = "删除年假规则")
    Result deleteAnnual(@LoginUser UserInfoVO userInfoVO, Long annualId) {
        return Result.success(attenceService.deleteAnnul(userInfoVO, annualId));
    }

    @RequestMapping("/annual/add")
    @ResponseBody
    @GetLog(value = "新增年假规则")
    Result addAnnual(@LoginUser UserInfoVO userInfoVO, @RequestBody AnnualVO annualVO) {
        return Result.success(attenceService.addAnnual(userInfoVO, annualVO));
    }

    @RequestMapping("/annual/update")
    @ResponseBody
    @GetLog(value = "更新年假规则")
    Result editAnnual(@LoginUser UserInfoVO userInfoVO, @RequestBody AnnualVO annualVO) {
        return Result.success(attenceService.editAnnual(userInfoVO, annualVO));
    }

    @RequestMapping("/annual/step/query")
    @ResponseBody
    Result queryAnnualStep(@LoginUser UserInfoVO userInfoVO, Long annualId) {
        return Result.success(attenceService.queryAnnualStep(userInfoVO, annualId));
    }

    @RequestMapping("/annual/step/add")
    @ResponseBody
    @GetLog(value = "新增年假阶梯设置")
    Result addAnnualStep(@LoginUser UserInfoVO userInfoVO, @RequestBody AnnualStepVO annualStepVO) {
        return Result.success(attenceService.addAnnualStep(userInfoVO, annualStepVO));
    }

    @RequestMapping("/annual/step/update")
    @ResponseBody
    @GetLog(value = "更新年假阶梯设置")
    Result updateAnnualStep(@LoginUser UserInfoVO userInfoVO, @RequestBody AnnualStepVO annualStepVO) {
        return Result.success(attenceService.editAnnualStep(userInfoVO, annualStepVO));
    }

    @RequestMapping("/annual/step/delete")
    @ResponseBody
    @GetLog(value = "删除年假阶梯设置")
    Result deleteAnnualStep(@LoginUser UserInfoVO userInfoVO, Long annualStepId) {
        return Result.success(attenceService.deleteAnnualStep(userInfoVO, annualStepId));
    }

    @RequestMapping("/workday/query")
    @ResponseBody
    Result queryWorkday(@LoginUser UserInfoVO userInfoVO, Long regulationId) {
        return Result.success(attenceService.queryWorkDays(userInfoVO, regulationId));
    }

    @RequestMapping("/workday/update")
    @ResponseBody
    @GetLog(value = "更新工作日")
    Result editWorkday(@LoginUser UserInfoVO userInfoVO, @RequestBody WorkdayVO workdayVO) {
        return Result.success(attenceService.setWorkday(userInfoVO, workdayVO));
    }

    @RequestMapping("/annual/report/query")
    @ResponseBody
    Result queryAnnualReport(@LoginUser UserInfoVO userInfoVO) {
        return Result.success(attenceService.queryAnnualDetail(userInfoVO));
    }

    @RequestMapping("/annual/report/update")
    @ResponseBody
    @GetLog(value = "更新个人假期详情")
    Result editAnnualReport(@LoginUser UserInfoVO userInfoVO, HolidayDetailVO holidayDetailVO) {
        return Result.success(attenceService.editAnnualDetail(userInfoVO, holidayDetailVO));
    }

    @RequestMapping("/report/all/query")
    @ResponseBody
    Result queryReportAll(@LoginUser UserInfoVO userInfoVO) {
        return Result.success(attenceService.queryAttenceReport(userInfoVO));
    }

    @RequestMapping("/report/detail/query")
    @ResponseBody
    Result queryReportDetail(@LoginUser UserInfoVO userInfoVO, @RequestBody PunchDetailVO punchDetailVO) {
        return Result.success(attenceService.queryAttencePunchDetail(userInfoVO, punchDetailVO));
    }

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}

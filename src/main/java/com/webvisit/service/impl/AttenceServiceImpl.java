package com.webvisit.service.impl;

import com.webvisit.common.exception.BusinessException;
import com.webvisit.dao.AttenceHolidayCustomExtMapper;
import com.webvisit.dao.AttenceHolidayDefaultExtMapper;
import com.webvisit.dao.AttenceRegulationExtMapper;
import com.webvisit.dao.common.AttenceRegulationMapper;
import com.webvisit.model.po.AttenceHolidayCustom;
import com.webvisit.model.po.AttenceHolidayDefault;
import com.webvisit.model.po.AttenceRegulation;
import com.webvisit.model.vo.HolidayVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.AttenceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/1
 */

@Service
public class AttenceServiceImpl implements AttenceService {
    @Resource
    private AttenceRegulationMapper attenceRegulationMapper;
    @Resource
    private AttenceRegulationExtMapper attenceRegulationExtMapper;
    @Resource
    private AttenceHolidayDefaultExtMapper attenceHolidayDefaultExtMapper;
    @Resource
    private AttenceHolidayCustomExtMapper attenceHolidayCustomExtMapper;

    @Override
    public Boolean addRegulation(AttenceRegulation attenceRegulation) {
        return attenceRegulationMapper.insert(attenceRegulation) == 1;
    }

    @Override
    public Boolean delRegulation(UserInfoVO userInfoVO, Long regulationId) {
        AttenceRegulation attenceRegulation = attenceRegulationMapper.selectByPrimaryKey(regulationId);
        if (null != attenceRegulation) {
            if (null != userInfoVO) {
                Long loginCompanyId = userInfoVO.getCompanyId();
                Long regulationCompanyId = attenceRegulation.getCompanyId();
                if (null != loginCompanyId && null != regulationCompanyId) {
                    if (loginCompanyId.equals(regulationCompanyId)) {
                        return attenceRegulationMapper.deleteByPrimaryKey(regulationId) == 1;
                    } else {
                        throw new BusinessException("你没有权限删除这个考勤规则！");
                    }
                } else {
                    throw new BusinessException("请绑定公司！");
                }
            } else {
                throw new BusinessException("获取用户信息失败，请重新登录");
            }
        } else {
            throw new BusinessException("没有查询到这个考勤规则");
        }
    }

    @Override
    public Boolean editRegulation(UserInfoVO userInfoVO, AttenceRegulation attenceRegulation) {
        Long regulationId = attenceRegulation.getId();
        AttenceRegulation queryRegulation = attenceRegulationMapper.selectByPrimaryKey(regulationId);
        if (null == queryRegulation) {
            throw new BusinessException("没有查询到这个考勤规则");
        }
        Long userCompanyId = userInfoVO.getCompanyId();
        Long queryRegulationCompanyId = queryRegulation.getCompanyId();
        if (null != queryRegulationCompanyId) {
            if (queryRegulationCompanyId.equals(userCompanyId)) {
                return attenceRegulationMapper.updateByPrimaryKey(attenceRegulation) == 1;
            } else {
                throw new BusinessException("你没有权限修改这个考勤规则！");
            }
        } else {
            throw new BusinessException("无效的考勤规则");
        }
    }

    @Override
    public List<AttenceRegulation> queryRegulations(Long companyId) {
        return attenceRegulationExtMapper.queryRegulationListByCompanyId(companyId);
    }

    @Override
    public List<HolidayVO> queryHolidays(UserInfoVO userInfoVO, Date beginDate, Date endDate) {
        return attenceHolidayCustomExtMapper.selectByDate(userInfoVO.getCompanyId(),beginDate,endDate);
    }
}

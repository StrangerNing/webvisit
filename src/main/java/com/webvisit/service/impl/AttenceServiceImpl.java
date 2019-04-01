package com.webvisit.service.impl;

import com.webvisit.dao.AttenceRegulationExtMapper;
import com.webvisit.dao.common.AttenceRegulationMapper;
import com.webvisit.model.po.AttenceRegulation;
import com.webvisit.service.AttenceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public Boolean addRegulation(AttenceRegulation attenceRegulation) {
        return attenceRegulationMapper.insert(attenceRegulation) == 1;
    }

    @Override
    public Boolean delRegulation(Long id) {
        return attenceRegulationMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public Boolean editRegulation(AttenceRegulation attenceRegulation) {
        return attenceRegulationMapper.updateByPrimaryKey(attenceRegulation) == 1;
    }

    @Override
    public List<AttenceRegulation> queryRequlations(Long companyId) {
        return attenceRegulationExtMapper.queryRegulationListByCompanyId(companyId);
    }
}

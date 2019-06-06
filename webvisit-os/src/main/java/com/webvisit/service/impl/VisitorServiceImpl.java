package com.webvisit.service.impl;

import com.webvisit.common.exception.BusinessException;
import com.webvisit.dao.VisitorExtMapper;
import com.webvisit.dao.common.VisitorMapper;
import com.webvisit.model.po.Visitor;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.model.vo.VisitorVO;
import com.webvisit.service.VisitorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/5/30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VisitorServiceImpl implements VisitorService {

    @Resource
    private VisitorMapper visitorMapper;
    @Resource
    private VisitorExtMapper visitorExtMapper;

    @Override
    public List<VisitorVO> queryVisitorList(VisitorVO visitor) {
        return visitorExtMapper.selectByCondition(visitor);
    }

    @Override
    public Boolean deleteVisitor(UserInfoVO userInfoVO, Long id) {
        Visitor visitor = visitorMapper.selectByPrimaryKey(id);
        Long companyId = userInfoVO.getCompanyId();
        if (!companyId.equals(visitor.getCompanyId())) {
            throw new BusinessException("你没有权限删除该访客！");
        }
        return visitorMapper.deleteByPrimaryKey(id) == 1;
    }
}

package com.webvisit.service;

import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.model.vo.VisitorVO;

import java.util.List;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/5/30
 */
public interface VisitorService {

    /**
     * 根据条件查询访客列表
     * @param visitor 查询条件
     * @return 访客列表
     */
    List<VisitorVO> queryVisitorList(VisitorVO visitor);

    /**
     * 删除访客信息
     * @param userInfoVO 当前用户信息
     * @param id 访客id
     * @return 删除结果
     */
    Boolean deleteVisitor(UserInfoVO userInfoVO, Long id);
}

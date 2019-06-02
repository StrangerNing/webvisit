package com.webvisit.dao;

import com.webvisit.model.vo.VisitorVO;

import java.util.List;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/5/30
 */
public interface VisitorExtMapper {

    /**
     * 根据条件查询访客
     * @param visitor 查询条件
     * @return 访客列表
     */
    List<VisitorVO> selectByCondition(VisitorVO visitor);
}

package com.webvisit.dao;

import com.webvisit.model.vo.LogVO;
import com.webvisit.model.vo.UserInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/14
 */
public interface LogExtMapper {

    /**
     * 根据公司id查询操作日志
     * @param userInfoVO 查询条件
     * @param logVO 查询条件
     * @return 操作日志
     */
    List<LogVO> queryLog(@Param("user") UserInfoVO userInfoVO, @Param("query") LogVO logVO);
}

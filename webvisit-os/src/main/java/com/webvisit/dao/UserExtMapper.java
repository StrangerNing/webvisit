package com.webvisit.dao;

import com.webvisit.model.dto.UserInfoDTO;
import com.webvisit.model.dto.UserSimpleDTO;
import com.webvisit.model.po.User;
import com.webvisit.model.vo.LoginVO;

import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/3
 */

public interface UserExtMapper {

    /**
     * 根据用户名获取用户信息
     *
     * @param loginVO 登录参数
     * @return 用户信息
     */
    UserInfoDTO selectByUsername(LoginVO loginVO);

    /**
     * 根据公司id查询用户列表
     *
     * @param companyId 公司id
     * @return 用户列表
     */
    List<UserSimpleDTO> selectByCompanyId(Long companyId);

    /**
     * 根据条件查询用户列表
     *
     * @param user 查询条件
     * @return 用户列表
     */
    List<User> selectByCondition(User user);
}

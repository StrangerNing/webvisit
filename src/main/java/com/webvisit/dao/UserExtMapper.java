package com.webvisit.dao;

import com.webvisit.model.dto.UserInfoDTO;
import com.webvisit.model.po.User;
import com.webvisit.model.vo.LoginVO;
import com.webvisit.model.vo.UserInfoVO;

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
}

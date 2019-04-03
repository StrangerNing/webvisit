package com.webvisit.service;

import com.webvisit.model.vo.LoginVO;
import com.webvisit.model.vo.UserInfoVO;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/3
 */

public interface LoginService {

    /**
     * 用户登录
     * @param loginVO 登录入参
     * @return 用户信息
     */
    UserInfoVO login(LoginVO loginVO);
}

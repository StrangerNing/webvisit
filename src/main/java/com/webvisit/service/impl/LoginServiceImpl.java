package com.webvisit.service.impl;

import com.webvisit.common.enums.UserStatusEnum;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.dao.UserExtMapper;
import com.webvisit.model.po.User;
import com.webvisit.model.vo.LoginVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/3
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserExtMapper userExtMapper;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public UserInfoVO login(LoginVO loginVO) {
        User user = userExtMapper.getUserByUsername(loginVO);
        if (null == user){
            throw new BusinessException("没有这个用户");
        }else if (UserStatusEnum.FREEZE.getCode().equals(user.getStatus())){
            throw new BusinessException("该用户账号已冻结");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(loginVO.getPassword(), user.getPassword())) {
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(user,userInfoVO);
            return userInfoVO;
        } else {
            throw new BusinessException("用户名或密码错误");
        }
    }
}

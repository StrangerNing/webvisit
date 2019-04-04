package com.webvisit.controller;

import com.webvisit.common.constant.LocalConstant;
import com.webvisit.common.re.Result;
import com.webvisit.model.vo.LoginVO;
import com.webvisit.model.vo.RegisterVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.LoginService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/3
 */

@Controller
@EnableAutoConfiguration
public class LoginController {

    @Resource
    private LoginService loginService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/login")
    @ResponseBody
    public Result login(LoginVO loginVO, HttpServletResponse response) {
        UserInfoVO userInfoVO = loginService.login(loginVO);
        if (null != userInfoVO) {
            String uuid = LocalConstant.LOGIN_UUID_KEY + UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(uuid, userInfoVO, 12, TimeUnit.HOURS);
            Cookie cookie = new Cookie(LocalConstant.LOGIN_USER_KEY, uuid);
            cookie.setMaxAge(-1);
            cookie.setDomain(".znzn.me");
            response.addCookie(cookie);
        }
        return Result.success(loginService.login(loginVO));
    }

    @RequestMapping("/register")
    @ResponseBody
    public Result register(RegisterVO registerVO) {
        return Result.success(loginService.register(registerVO));
    }
}

package com.webvisit.controller;

import com.webvisit.common.constant.LocalConstant;
import com.webvisit.common.component.Result;
import com.webvisit.model.vo.LoginVO;
import com.webvisit.model.vo.RegisterVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.LoginService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody LoginVO loginVO) {
        UserInfoVO userInfoVO = loginService.login(loginVO);
        if (null != userInfoVO) {
            String uuid = LocalConstant.LOGIN_UUID_KEY + UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(uuid, userInfoVO, LocalConstant.LOGIN_USER_EXPOSE_TIME, TimeUnit.HOURS);
            return Result.success(uuid);
        }else {
            return Result.failure();
        }
    }

    @RequestMapping(value = "/user/info")
    @ResponseBody
    public Result getUserInfo(HttpServletRequest request){
        return Result.success(loginService.getUserInfoByToken(request));
    }

    @RequestMapping("/register")
    @ResponseBody
    public Result register(RegisterVO registerVO) {
        return Result.success(loginService.register(registerVO));
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request){
        if (loginService.logout(request)) {
            return Result.success();
        } else {
            return Result.failure();
        }
    }
}

package com.webvisit.common.handler;

import com.webvisit.common.annotation.LoginUser;
import com.webvisit.common.constant.LocalConstant;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.model.vo.UserInfoVO;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/4
 */

public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private RedisTemplate<String, Object> redisTemplate;

    public LoginUserArgumentResolver(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(LoginUser.class) != null
                && parameter.getParameterType() == UserInfoVO.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String accessToken = request.getHeader(LocalConstant.LOGIN_USER_KEY);
        UserInfoVO userInfoVO = (UserInfoVO)redisTemplate.opsForValue().get(accessToken);
        if (null == userInfoVO){
            throw new BusinessException("获取用户信息失败，请重新登录");
        }
        if (null == userInfoVO.getCompanyId()){
            throw new BusinessException("请先绑定公司！");
        }
        return userInfoVO;
    }
}

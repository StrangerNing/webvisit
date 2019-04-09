package com.webvisit.service.impl;

import com.webvisit.common.constant.LocalConstant;
import com.webvisit.common.enums.UserStatusEnum;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.dao.UserExtMapper;
import com.webvisit.dao.common.UserMapper;
import com.webvisit.model.dto.UserInfoDTO;
import com.webvisit.model.po.User;
import com.webvisit.model.vo.LoginVO;
import com.webvisit.model.vo.RegisterVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.LoginService;
import com.webvisit.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
    private UserMapper userMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public UserInfoVO login(LoginVO loginVO) {
        if (StringUtils.isEmpty(loginVO.getUsername())){
            throw new BusinessException("请输入用户名");
        } else if (StringUtils.isEmpty(loginVO.getPassword())) {
            throw new BusinessException("请输入密码");
        }
        UserInfoDTO user = userExtMapper.selectByUsername(loginVO);
        if (null == user){
            throw new BusinessException("没有这个用户");
        }else if (UserStatusEnum.FREEZE.getCode().equals(user.getStatus())){
            throw new BusinessException("该用户账号已冻结");
        }
        if (MD5Util.verify(loginVO.getPassword(),user.getPassword())) {
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(user,userInfoVO);
            return userInfoVO;
        } else {
            throw new BusinessException("用户名或密码错误");
        }
    }

    @Override
    public Boolean register(RegisterVO registerVO) {
        String password = registerVO.getPassword();
        String checkPassword = registerVO.getCheckPassword();
        String cryptPassword = "";
        if (StringUtils.isNotEmpty(password)) {
            if (StringUtils.isNotEmpty(checkPassword)) {
                if (StringUtils.equals(password, checkPassword)) {
                    cryptPassword = MD5Util.generate(password);
                } else {
                    throw new BusinessException("两次密码不一致！");
                }
            } else{
                throw new BusinessException("请输入确认密码！");
            }
        } else {
            throw new BusinessException("请输入密码！");
        }
        User user = new User();
        user.setUsername(registerVO.getUsername());
        user.setPassword(cryptPassword);
        return userMapper.insert(user) == 1;
    }

    @Override
    public Boolean logout(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(LocalConstant.LOGIN_USER_KEY)){
                String uuidKey = cookie.getValue();
                return redisTemplate.delete(uuidKey);
            }
        }
        throw new BusinessException("登出失败");
    }

    @Override
    public UserInfoVO getUserInfoByToken(String token) {
        if (StringUtils.isEmpty(token)){
            throw new BusinessException("未登录，请先登录");
        }
        UserInfoVO userInfoVO = (UserInfoVO)redisTemplate.opsForValue().get(token);
        if (null == userInfoVO){
            throw new BusinessException("获取用户信息失败，请重新登录");
        }
        if (null == userInfoVO.getCompanyId()){
            throw new BusinessException("请先绑定公司！");
        }
        return userInfoVO;
    }
}

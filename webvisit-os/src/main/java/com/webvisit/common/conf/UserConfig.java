package com.webvisit.common.conf;

import com.webvisit.WebvisitApplication;
import com.webvisit.common.handler.LoginUserArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/9
 */

@SpringBootConfiguration
public class UserConfig implements WebMvcConfigurer {

    @Resource
    private RedisTemplate redisTemplate;

    public static void main(String[] args){
        SpringApplication.run(WebvisitApplication.class);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(new LoginUserArgumentResolver(redisTemplate));
    }
}

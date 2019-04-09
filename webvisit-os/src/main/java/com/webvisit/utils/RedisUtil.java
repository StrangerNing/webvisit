package com.webvisit.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/10
 */
@Component
public class RedisUtil {
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    public Object get(String key){
        if (!StringUtils.isEmpty(key)){
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }
}

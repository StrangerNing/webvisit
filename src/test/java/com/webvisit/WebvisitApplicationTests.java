package com.webvisit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebvisitApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("test","success");
        Object test = redisTemplate.opsForValue().get("test");
        System.out.println(test);
    }

}

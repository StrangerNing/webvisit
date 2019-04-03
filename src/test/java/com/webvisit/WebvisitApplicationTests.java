package com.webvisit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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
        redisTemplate.opsForValue().set("test","success!!",10,TimeUnit.SECONDS);
        Object test = redisTemplate.opsForValue().get("test");
        System.out.println(test);
    }

    @Test
    public void testRedisUtil(){
    }

}

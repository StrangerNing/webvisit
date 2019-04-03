package com.webvisit;

import com.webvisit.model.vo.LoginVO;
import com.webvisit.model.vo.RegisterVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.LoginService;
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
    @Resource
    private LoginService loginService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("test", "success!!", 10, TimeUnit.SECONDS);
        Object test = redisTemplate.opsForValue().get("test");
        System.out.println(test);
    }

    @Test
    public void testRedisUtil() {
    }

    @Test
    public void testQueryUser() {
        UserInfoVO userInfoVO = loginService.login(LoginVO.builder().username("zhuzening").password("zhuzening8").build());
        System.out.println(userInfoVO);
    }

    @Test
    public void testRegisterUser() {
        RegisterVO registerVO = new RegisterVO("zhuzening","zhuzening8","zhuzening8");
        System.out.println(loginService.register(registerVO));
    }

}

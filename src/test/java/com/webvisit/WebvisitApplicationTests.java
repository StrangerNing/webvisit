package com.webvisit;

import com.webvisit.model.vo.HolidayVO;
import com.webvisit.model.vo.LoginVO;
import com.webvisit.model.vo.RegisterVO;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.AttenceService;
import com.webvisit.service.LoginService;
import com.webvisit.utils.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebvisitApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private LoginService loginService;
    @Resource
    private AttenceService attenceService;

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
        UserInfoVO userInfoVO = UserInfoVO.builder().nickname("朱泽宁").username("zhuzening").email("zhuzening@foxmail.com").build();
        redisTemplate.opsForValue().set("user",userInfoVO,10,TimeUnit.MINUTES);
        Object test = redisTemplate.opsForValue().get("user");
        System.out.println(test);
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

    @Test
    public void testQueryHolidays(){
        UserInfoVO userInfoVO = UserInfoVO.builder().companyId(1L).build();
        List<HolidayVO> holidays = attenceService.queryHolidays(userInfoVO, TimeUtil.createTime(2019,0,1),TimeUtil.createTime(2019,7,30));
        for (HolidayVO holiday : holidays){
            System.out.println(holiday);
        }
    }

}

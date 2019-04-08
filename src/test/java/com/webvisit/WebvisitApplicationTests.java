package com.webvisit;

import com.webvisit.common.enums.AnnualBaseEnum;
import com.webvisit.dao.AttenceLeaveExtMapper;
import com.webvisit.model.po.AttenceLeave;
import com.webvisit.model.vo.*;
import com.webvisit.service.AttenceService;
import com.webvisit.service.LoginService;
import com.webvisit.utils.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
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
    @Resource
    private AttenceLeaveExtMapper attenceLeaveExtMapper;

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
        redisTemplate.opsForValue().set("user", userInfoVO, 10, TimeUnit.MINUTES);
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
        RegisterVO registerVO = new RegisterVO("test2", "test2", "test2");
        System.out.println(loginService.register(registerVO));
    }

    @Test
    public void testQueryHolidays() {
        UserInfoVO userInfoVO = UserInfoVO.builder().companyId(1L).build();
        List<HolidayVO> holidays = attenceService.queryHolidays(userInfoVO, TimeUtil.createTime(2019, 6, 11), TimeUtil.createTime(2019, 6, 11));
        for (HolidayVO holiday : holidays) {
            System.out.println(holiday);
        }
        System.out.println(holidays == null);
    }

    @Test
    public void testAddLeave() {
        LeaveVO attenceLeave = new LeaveVO();
        attenceLeave.setName("测试");
        attenceLeave.setAvailableDays(7);
        attenceLeave.setSalaryPercent(new BigDecimal(0.70));
        UserInfoVO userInfoVO = UserInfoVO.builder().companyId(2L).id(2L).build();
        System.out.println(attenceService.addLeave(userInfoVO, attenceLeave));
    }

    @Test
    public void testQueryLeave() {
        AttenceLeave attenceLeave = new AttenceLeave();
        attenceLeave.setCompanyId(1L);
        attenceLeave.setName("测试2");
        System.out.println(attenceLeaveExtMapper.selectByCondition(attenceLeave));
    }

    @Test
    public void testDeleteLeave() {
        UserInfoVO userInfoVO = UserInfoVO.builder().companyId(1L).build();
        attenceService.deleteLeave(userInfoVO,5L);
    }

    @Test
    public void testAddAnnual(){
        UserInfoVO userInfoVO = UserInfoVO.builder().id(1L).companyId(1L).build();
        AnnualVO annualVO = new AnnualVO();
        annualVO.setStatus(AnnualBaseEnum.AnnualStatusEnum.ENABLE.getCode());
        annualVO.setAccumulateToNextYear(AnnualBaseEnum.AnnualAccumulateEnum.DISABLE.getCode());
        annualVO.setProbationHas(AnnualBaseEnum.AnnualProbationHasEnum.ENABLE.getCode());
        annualVO.setGraduationOneYearHas(AnnualBaseEnum.AnnualGraduationOneYearHasEnum.ENABLE.getCode());
        annualVO.setExpireDate(TimeUtil.createTime(2019,0,1));
        attenceService.addAnnual(userInfoVO,annualVO);
    }

    @Test
    public void testDeleteAnnual(){
        UserInfoVO userInfoVO = UserInfoVO.builder().id(1L).companyId(1L).build();
        attenceService.deleteAnnul(userInfoVO,6L);
    }

    @Test
    public void testtest(){
        Date date = TimeUtil.createTime(2019,3,7);
        System.out.println("日期:"+date+" 星期 "+TimeUtil.getDayOfWeek(date));
    }

    @Test
    public void testQueryAttenceReport(){
        UserInfoVO userInfoVO = UserInfoVO.builder().companyId(2L).build();
        List<AttenceReportVO> attenceReportVOList = attenceService.queryAttenceReport(userInfoVO);
        for (AttenceReportVO attenceReportVO : attenceReportVOList){
            System.out.println(attenceReportVO);
        }
    }

    @Test
    public void testQueryPunchDetail(){
        PunchDetailVO punchDetailVO = new PunchDetailVO();
        UserInfoVO userInfoVO = UserInfoVO.builder().companyId(1L).build();
        List<PunchDetailVO> punchDetailVOList = attenceService.queryAttencePunchDetail(userInfoVO,punchDetailVO);
        for (PunchDetailVO punchDetail : punchDetailVOList){
            System.out.println(punchDetail);
        }
    }

}

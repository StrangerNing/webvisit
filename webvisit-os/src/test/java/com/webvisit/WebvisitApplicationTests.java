package com.webvisit;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.webvisit.common.enums.AnnualBaseEnum;
import com.webvisit.dao.AttenceLeaveExtMapper;
import com.webvisit.dao.common.AttenceHolidayDetailMapper;
import com.webvisit.dao.common.AttencePunchDetailMapper;
import com.webvisit.model.po.AttenceHolidayDetail;
import com.webvisit.model.po.AttenceLeave;
import com.webvisit.model.po.AttencePunchDetail;
import com.webvisit.model.vo.*;
import com.webvisit.service.AttenceService;
import com.webvisit.service.LoginService;
import com.webvisit.utils.MD5Util;
import com.webvisit.utils.TimeUtil;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.SimpleFormatter;

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
    @Resource
    private AttenceHolidayDetailMapper attenceHolidayDetailMapper;
    @Resource
    private AttencePunchDetailMapper punchDetailMapper;
    @Resource
    private FastFileStorageClient storageClient;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRedis() {
//        redisTemplate.opsForValue().set("test", "success!!", 10, TimeUnit.SECONDS);
        UserInfoVO test = (UserInfoVO) redisTemplate.opsForValue().get("login_1ff59a65-8e28-4e56-a243-bb4ee58098b4");
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
    public void testGenerateMd5() {
        String password = "zhuzening8";
        String encryptPassword = MD5Util.generate(password);
        System.out.println(encryptPassword);
    }

    @Test
    public void testQueryUser() {
        UserInfoVO userInfoVO = loginService.login(LoginVO.builder().username("zhuzening").password("zhuzening8").build());
        System.out.println(userInfoVO);
    }

    @Test
    public void testRegisterUser() {
        RegisterVO registerVO = new RegisterVO("test4", "zhuzening8", "zhuzening8");
        System.out.println(loginService.register(registerVO));
    }

    @Test
    public void testInsertLeaveReport() {
        AttenceHolidayDetail attenceHolidayDetail = new AttenceHolidayDetail();
        attenceHolidayDetail.setEmpId(2L);
        attenceHolidayDetail.setLeaveType(0);
        attenceHolidayDetail.setLeaveId(2L);
        attenceHolidayDetail.setThisYearLeaveLeft(1);
        attenceHolidayDetail.setLastYearLeaveLeft(0);
        attenceHolidayDetail.setVacationNumber(3);
        attenceHolidayDetailMapper.insert(attenceHolidayDetail);
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
    public void testInsertPunchDetail() throws ParseException {
        int month = 0;
        int date = 1;
        for (month = 0; month < 4; month++) {
            for (date = 1; date < 31; date++) {
                AttencePunchDetail punchDetail = new AttencePunchDetail();
                punchDetail.setEmpId(1L);
                punchDetail.setPunchType(0);
//                punchDetail.setPunchInTime(TimeUtil.createTime(2019,month,date,8,32,8));
//                punchDetail.setPunchOutTime(TimeUtil.createTime(2019,month,date,18,44,9));
                punchDetail.setPunchInStatus(0);
                punchDetail.setPunchOutStatus(0);
                String getDate = "2019-" + month + "-" + date;
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                punchDetail.setPunchTime(TimeUtil.createTime(2019, month, date));
                punchDetailMapper.insert(punchDetail);
            }
        }
    }

    @Test
    public void testDeletePunchDetail() {
        for (Long i = 242L; i < 362L; i++) {
            punchDetailMapper.deleteByPrimaryKey(i);
        }
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
        attenceService.deleteLeave(userInfoVO, 5L);
    }

    @Test
    public void testAddAnnual() {
        UserInfoVO userInfoVO = UserInfoVO.builder().id(1L).companyId(1L).build();
        AnnualVO annualVO = new AnnualVO();
        annualVO.setStatus(AnnualBaseEnum.AnnualStatusEnum.ENABLE.getCode());
        annualVO.setAccumulateToNextYear(AnnualBaseEnum.AnnualAccumulateEnum.DISABLE.getCode());
        annualVO.setProbationHas(AnnualBaseEnum.AnnualProbationHasEnum.ENABLE.getCode());
        annualVO.setGraduationOneYearHas(AnnualBaseEnum.AnnualGraduationOneYearHasEnum.ENABLE.getCode());
        annualVO.setExpireDate(TimeUtil.createTime(2019, 0, 1));
        attenceService.addAnnual(userInfoVO, annualVO);
    }

    @Test
    public void testDeleteAnnual() {
        UserInfoVO userInfoVO = UserInfoVO.builder().id(1L).companyId(1L).build();
        attenceService.deleteAnnul(userInfoVO, 6L);
    }

    @Test
    public void testtest() {
        Date date = TimeUtil.createTime(2019, 3, 7);
        System.out.println("日期:" + date + " 星期 " + TimeUtil.getDayOfWeek(date));
    }

    @Test
    public void MD5Encrypt() {
        String password = MD5Util.generate("zzznnn8.");
        System.out.println(password);
    }

    @Test
    public void testQueryAttenceReport() {
        UserInfoVO userInfoVO = UserInfoVO.builder().companyId(2L).build();
        List<AttenceReportVO> attenceReportVOList = attenceService.queryAttenceReport(userInfoVO);
        for (AttenceReportVO attenceReportVO : attenceReportVOList) {
            System.out.println(attenceReportVO);
        }
    }

    @Test
    public void testQueryPunchDetail() {
        PunchDetailVO punchDetailVO = new PunchDetailVO();
        UserInfoVO userInfoVO = UserInfoVO.builder().companyId(1L).build();
        List<PunchDetailVO> punchDetailVOList = attenceService.queryAttencePunchDetail(userInfoVO, punchDetailVO);
        for (PunchDetailVO punchDetail : punchDetailVOList) {
            System.out.println(punchDetail);
        }
    }

    @Test
    public void testGetExtName() {
        String filename = "abc.a.jpg";
        String prefix = filename.substring(filename.lastIndexOf(".")+1);
        System.out.println(prefix);
    }

}

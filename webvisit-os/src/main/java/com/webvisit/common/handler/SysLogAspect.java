package com.webvisit.common.handler;

import com.alibaba.fastjson.JSON;
import com.webvisit.common.annotation.GetLog;
import com.webvisit.common.constant.LocalConstant;
import com.webvisit.model.po.Log;
import com.webvisit.model.vo.UserInfoVO;
import com.webvisit.service.SysLogService;
import com.webvisit.utils.TimeUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/8
 */

@Aspect
@Component
public class SysLogAspect {

    @Resource
    private SysLogService sysLogService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Pointcut("@annotation(com.webvisit.common.annotation.GetLog)")
    public void logPointCut(){

    }

    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint){

        Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

        Log log = new Log();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        GetLog getLog = method.getAnnotation(GetLog.class);
        if (getLog != null){
            String value = getLog.value();
            //获取注解的value值保存到操作字段中
            log.setOperation(value);
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        log.setMethod(className+"."+methodName);

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null != requestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            //获取请求的参数
            Map<String,String[]> params = request.getParameterMap();
            log.setParams(JSON.toJSONString(params));
            //获取用户名
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(LocalConstant.LOGIN_USER_KEY)){
                    String uuid = cookie.getValue();
                    UserInfoVO userInfoVO = (UserInfoVO)redisTemplate.opsForValue().get(uuid);
                    if (null != userInfoVO) {
                        log.setUsername(userInfoVO.getUsername());
                        log.setCompanyId(userInfoVO.getCompanyId());
                    } else {
                        logger.error("获取用户名失败");
                    }
                }
            }
            //获取操作者ip
            String ip = request.getRemoteAddr();
            log.setIp(ip);
            log.setCreateTime(TimeUtil.createNowTime());
        }
        if (!sysLogService.saveLog(log)){
            logger.error("保存日志失败");
        }
    }

}

package com.webvisit.common.handler;

import com.webvisit.common.enums.ResultEnum;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.common.component.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/10
 */

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof BusinessException){
            BusinessException businessException = (BusinessException)e;
            return Result.error(((BusinessException) e).getTextMessage(),((BusinessException) e).getCode());
        }else {
            logger.error("系统异常，{}",e);
            return Result.error("系统异常", ResultEnum.SystemException);
        }
    }
}

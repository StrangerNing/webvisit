package com.webvisit.common.handler;

import com.webvisit.common.exception.BusinessException;
import com.webvisit.common.re.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/2
 */

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(Exception.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            return Result.error(businessException.getMessage(), businessException.getCode());
        } else {
            logger.error("系统异常",e);
            e.printStackTrace();
            return Result.error("系统异常",Result.CODE_OTHER);
        }
    }
}

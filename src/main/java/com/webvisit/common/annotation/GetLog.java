package com.webvisit.common.annotation;

import java.lang.annotation.*;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/8
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GetLog {
    String value() default "";
}

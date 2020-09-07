package com.mx.framework.annotation.aop;

import java.lang.annotation.*;

/**
 * @author zheng.liming
 * @date 2019/8/26
 * @description 异常通知
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AfterThrows {
    String pointcup();
}
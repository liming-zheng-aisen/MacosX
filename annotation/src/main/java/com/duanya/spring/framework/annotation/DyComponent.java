package com.duanya.spring.framework.annotation;


import java.lang.annotation.*;

/**
 * @author zheng.liming
 * @date 2019/8/5
 * @description
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
public @interface DyComponent {
    String value() default "";
}
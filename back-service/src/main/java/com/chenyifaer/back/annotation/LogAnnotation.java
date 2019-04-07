package com.chenyifaer.back.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义操作日志注解
 * @Author:wudh
 * @Date: 2019/4/7 19:56
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {

    String menuName() default "";

    String action() default "";

    String operation() default "";

}

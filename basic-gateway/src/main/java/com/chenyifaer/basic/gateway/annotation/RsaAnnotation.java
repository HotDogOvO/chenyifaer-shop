package com.chenyifaer.basic.gateway.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义RSA加解密注解
 * @Author:wudh
 * @Date: 2019/4/14 16:12
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RsaAnnotation {

	/**
	 * 入参是否解密,默认解密
	 */
	boolean inDecode() default true;
	
	/**
	 * 出参是否加密,默认加密
	 */
	boolean outEncode() default true;
}

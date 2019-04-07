package com.chenyifaer.back.aop;

import com.alibaba.fastjson.JSONObject;
import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.controller.BaseController;
import com.chenyifaer.back.entity.po.LogPO;
import com.chenyifaer.back.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 自定义日志 - AOP
 * @Author:wudh
 * @Date: 2019/4/7 19:49
 */
@Slf4j
@Aspect
@Component
public class LogAop extends BaseController {

    @Autowired
    private LogService logService;

    @Around(value = "@annotation(com.chenyifaer.back.annotation.LogAnnotation)")
    public Object logSave(ProceedingJoinPoint joinPoint) throws Throwable {

        // 获得注解注释的方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // 获得具体注解
        LogAnnotation logAnnotation = methodSignature.getMethod().getDeclaredAnnotation(LogAnnotation.class);
        // 获得参数名
        String[] paramNames = methodSignature.getParameterNames();
        // 防止传入空参数也会执行
        if (paramNames != null && paramNames.length > 0) {
            // 通过joinPoint获得参数值
            Object[] args = joinPoint.getArgs();
            HashMap<Object, Object> params = new HashMap<>();
            for (int i = 0; i < paramNames.length; i++) {
                Object value = args[i];
                if (value instanceof Serializable) {
                    // 将参数值放入map
                    params.put(paramNames[i], value);
                }
                try {
                    log.debug("参数记录成功:{自定义环绕注解存入的参数为" + JSONObject.toJSONString(params) + "}");
                } catch (Exception e) {
                    log.error("参数记录失败{}", "错误的原因是", e);
                }
            }
        }
        try {
            //新建日志实体
            LogPO logPo = new LogPO();
            //记录日志目录
            logPo.setMenuName(logAnnotation.menuName());
            //记录操作
            logPo.setAction(logAnnotation.action());
            //记录操作内容
            logPo.setOperation(logAnnotation.operation());
            //设置操作人
            logPo.setUserId(getUserId());
            logService.save(logPo);
            //执行原方法
            Object object = joinPoint.proceed();
            return object;
        } catch (Exception e) { // 方法执行失败
            log.error("方法执行失败", "错误的原因是", e);
            throw e;
        }
    }
}

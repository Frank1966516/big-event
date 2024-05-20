package com.itheima.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
@Aspect
public class TimeOperate {
    @Pointcut("@annotation(com.itheima.anno.Update)")
    public void pointCutUpdate(){}

    // 通用更新的环绕操作
    @Around("pointCutUpdate()")
    public Object aroundUpdate(ProceedingJoinPoint joinPoint) throws Throwable {

        // 前置操作

        // 获取参数
        Object[] args = joinPoint.getArgs();
        Object arg = args[0];

        // 通过反射修改参数的更新时间
        Method setUpdateTime = arg.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
        setUpdateTime.invoke(arg, LocalDateTime.now());

        // 修改参数
        args[0] = arg;

        // 方法操作
        Object result = joinPoint.proceed(args);

        // 后置操作
        return result;
    }

}

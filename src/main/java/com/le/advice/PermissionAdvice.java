package com.le.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xll
 * @Date: 2022/4/9 8:35
 */
@Aspect
@Component
@Order(0) // 决定切面执行顺序，数值越小，优先执行
public class PermissionAdvice {
    // 可使用注解或者路径表达式execution
    @Pointcut("@annotation(com.le.annocation.PermissionAnnotation)")
    private void check(){}

    @Around("check()")
    public Object PermissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("===================第一个切面===================：" + System.currentTimeMillis());

        // 1. 获取签名 包路径+类名+方法名
        Signature signature = joinPoint.getSignature();
        // 包路径+类名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取方法名
        String name = signature.getName();

        // 2. 获取参数
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        Long id = ((JSONObject) args[0]).getLong("id");
        String name_r = ((JSONObject) args[0]).getString("name");

        // 3. 获取请求ip
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("declaringTypeName", declaringTypeName);
        map.put("id", id);
        map.put("name_r", name_r);
        map.put("url", url);
        map.put("ip", ip);

        System.out.println(JSON.toJSONString(map));

        return joinPoint.proceed();
    }
}

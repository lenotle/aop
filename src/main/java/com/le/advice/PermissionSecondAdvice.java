package com.le.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xll
 * @Date: 2022/4/9 8:35
 */
@Aspect
@Component
@Order(1)
public class PermissionSecondAdvice {
    // 可使用注解或者路径表达式execution
    @Pointcut("@annotation(com.le.annocation.PermissionAnnotation)")
    private void check(){}

    @Around("check()")
    public Object PermissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("===================第二个切面===================：" + System.currentTimeMillis());

        //获取请求参数，详见接口类
        Object[] objects = joinPoint.getArgs();
        Long id = ((JSONObject) objects[0]).getLong("id");
        String name = ((JSONObject) objects[0]).getString("name");
        System.out.println("id->>>>>>>>>>>>>>>>>>>>>>" + id);
        System.out.println("name->>>>>>>>>>>>>>>>>>>>>>" + name);

        // name不是管理员则抛出异常
        if (!name.equals("admin")) {
            return JSON.parseObject("{\"message\":\"not admin\",\"code\":403}");
        }
        // 方法执行执行，可对返回值进行增强
        Object obj =  joinPoint.proceed();
        System.out.println(obj + "enhance");
        return obj;

    }
}

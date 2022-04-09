package com.le.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.le.annocation.PermissionAnnotation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: xll
 * @Date: 2022/4/9 8:52
 */
@RestController
@RequestMapping("/aop")
public class PermissionController {

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @PermissionAnnotation
    public JSONObject test(@RequestBody JSONObject body)
    {
        System.out.println("-------------------execute method-------------" + System.currentTimeMillis());
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }
}

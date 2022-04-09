package com.le.annocation;

import java.lang.annotation.*;

/**
 * @Auther: xll
 * @Date: 2022/4/9 8:27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented()
public @interface PermissionAnnotation  {
}

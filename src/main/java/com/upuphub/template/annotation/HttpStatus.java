package com.upuphub.template.annotation;

import java.lang.annotation.*;

/**
 * @author 王志立
 * @Project: basic
 * @Package:cc.wangzl.annotation
 * @date 2018/9/19 15:52
 * @description HTTP状态码注解
 **/
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpStatus {
}

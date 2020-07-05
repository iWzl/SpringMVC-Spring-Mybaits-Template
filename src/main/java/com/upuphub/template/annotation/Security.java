package com.upuphub.template.annotation;

import com.upuphub.template.enums.RoleEnum;

import java.lang.annotation.*;

/**
 * @author 王志立
 * @Project: basic
 * @Package:cc.wangzl.annotation
 * @date 2018/9/19 15:52
 * @description 安全权限注解
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Security {
    RoleEnum[] roles() default {};
    boolean createToken() default false;
    boolean checkToken() default true;
}

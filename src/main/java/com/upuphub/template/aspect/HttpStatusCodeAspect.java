package com.upuphub.template.aspect;

import com.upuphub.template.annotation.HttpStatus;
import com.upuphub.template.util.http.RequestUtil;
import com.upuphub.template.util.http.message.Msg;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;


/**
 * @author 王志立
 * @Project: basic
 * @Package:cc.wangzl.aspect
 * @date 2018/9/19 15:56
 * @description 安全切面
 **/
@Order(1)
@Aspect
public class HttpStatusCodeAspect {

    @Pointcut("execution(* cc.wangzl.controller..*.*(..))")
    public void pointCut() {
    }

    @AfterReturning(returning = "msg",value = "pointCut()&&@annotation(httpStatus)")
    private static void setHttpStatusCode(Msg msg, HttpStatus httpStatus) {
        RequestUtil.getResponse().setStatus(msg.getMeta().getCode());
    }
}

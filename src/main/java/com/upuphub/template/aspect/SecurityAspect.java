package com.upuphub.template.aspect;

import com.upuphub.template.annotation.Security;
import com.upuphub.template.enums.ResultEnum;
import com.upuphub.template.enums.RoleEnum;
import com.upuphub.template.util.http.message.Msg;
import com.upuphub.template.util.http.RequestUtil;
import com.upuphub.template.util.type.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/**
 * @author 王志立
 * @Project: basic
 * @Package:cc.wangzl.aspect
 * @date 2018/9/19 15:56
 * @description 安全切面
 **/
@Order(2)
@Aspect
public class SecurityAspect {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* cc.wangzl.controller..*.*(..))")
    public void pointCut() {
    }

    @Around(value = "pointCut()&&@annotation(security)")
    public Object roleAop(ProceedingJoinPoint point, Security security) throws Throwable {
        if (security.checkToken()) {
            String sessionToken = RequestUtil.getSessionToken();
            log.info("正确令牌:" + sessionToken);
            String requestToken = RequestUtil.getRequestToken();
            log.info("请求令牌:" + requestToken);
            if(StringUtil.isEmpty(sessionToken)){
                return  Msg.fail(ResultEnum.UNAUTHORIZED,"请先登录");
            }
            if ( StringUtil.isEmpty(requestToken)) {
               return  Msg.fail(ResultEnum.UNAUTHORIZED,"X-Token令牌为空",true);
            }
            if (!requestToken.equals(sessionToken)) {
                return  Msg.fail(ResultEnum.UNAUTHORIZED,"X-Token令牌不正确");
            }
        }
        if (security.createToken()) {
            RequestUtil.createToken();
            log.info("创建X-Token令牌:" + RequestUtil.getSessionToken());
        }
        RoleEnum[] roles = security.roles();
        if (roles.length > 0) {
            if (roles[0] == RoleEnum.LOG) {
                return point.proceed();
            }
            for (RoleEnum role : roles) {
                if (role == RequestUtil.getRole()) {
                    return point.proceed();
                }
            }
        }
        return Msg.fail(ResultEnum.PRECONDITION_FAILED,"没有权限");
    }
}

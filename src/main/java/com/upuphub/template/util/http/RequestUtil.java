package com.upuphub.template.util.http;

import com.upuphub.template.config.BaseStatic;
import com.upuphub.template.enums.RoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 王志立
 * @Project: basic
 * @Package:cc.wangzl.util.http
 * @date 2018/8/31 10:59
 * @description 登录、注销等session和HTTP相关的操作
 **/
public class RequestUtil {
    private static Logger log = LoggerFactory.getLogger(RequestUtil.class);

    public static void login(String name, RoleEnum role) {
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        Cookie nameCookie = new Cookie(BaseStatic.COOKIE_NAME, name);
        nameCookie.setPath(BaseStatic.COOKIE_PATH);
        response.addCookie(nameCookie);
        request.getSession().setAttribute(BaseStatic.KEY_SESSION_ROLE, role);
    }

    public static void login(RoleEnum role) {
        HttpServletRequest request = getRequest();
        request.getSession().setAttribute(BaseStatic.KEY_SESSION_ROLE, role);
    }

    public static void logout() {
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        Cookie nameCookie = new Cookie(BaseStatic.COOKIE_NAME, (String) request.getSession().getAttribute(BaseStatic.KEY_SESSION_NAME));
        nameCookie.setMaxAge(0);
        nameCookie.setPath(BaseStatic.COOKIE_PATH);
        response.addCookie(nameCookie);
        request.getSession().invalidate();
    }

    public static RoleEnum getRole() {
        Object obj = getRequest().getSession().getAttribute(BaseStatic.KEY_SESSION_ROLE);
        log.info("role=" + obj);
        if (obj == null) {
            return null;
        }
        return (RoleEnum) obj;
    }

    public static String getSessionToken() {
        HttpServletRequest req = getRequest();
        return (String) req.getSession().getAttribute(BaseStatic.KEY_SESSION_TOKEN);

    }

    public static String getRequestToken() {
        return getRequest().getHeader(BaseStatic.KEY_SESSION_TOKEN);
    }

    public static void createToken() {
        HttpServletRequest req = getRequest();
        req.getSession().setAttribute(BaseStatic.KEY_SESSION_TOKEN, generateToken());
    }

    public static boolean isAjax() {
        return "XMLHttpRequest".equals(getRequest().getHeader("X-Requested-With")) || "XMLHttpRequest".equals(getRequest().getParameter("X-Requested-With"));
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    private static String generateToken() {
        try {
            return UUID.randomUUID().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.upuphub.template.enums;

/**
 * @author 王志立
 * @Project: basic
 * @Package:cc.wangzl.enums
 * @date 2018/8/29 11:04
 * @description HTTP状态码枚举
 **/
public enum ResultEnum {

    //请求成功
    SUCCESS(200),
    //客户端请求的语法错误，服务器无法理解
    BAD_REQUEST(400),
    //请求要求用户的身份认证
    UNAUTHORIZED(401),
    //服务器理解请求客户端的请求，但是拒绝执行此请求
    FORBIDDEN(403),
    //服务器无法根据客户端的请求找到资源(网页)
    NOT_FOUND(404),
    //客户端请求中的方法被禁止
    METHOD_NOT_ALLOWED(405),
    //服务器无法根据客户端请求的内容特性完成请求
    NOT_ACCEPTABLE(406),
    //客户端请求信息的先决条件错误
    PRECONDITION_FAILED(412),
    //服务器无法处理请求附带的媒体格式
    UNSUPPORTED_MEDIA_TYPE(415),
    //服务器内部错误，无法完成请求
    INTERNAL_SERVER_ERROR(500),
    ;

    private int code;

    ResultEnum(int code) {
        this.code = code;
    }

    public int value() {
        return this.code;
    }
}

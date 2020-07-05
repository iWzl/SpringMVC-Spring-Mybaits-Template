package com.upuphub.template.util.http.message;

import com.upuphub.template.util.http.RequestUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @program: basic
 * @description: 返回消息的基本基本信息
 * @author: 王志立
 * @create: 2018-12-20 08:42
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meta {
    private Integer code;
    private String message;
    private String token;

    private Meta() {
        //构造器隐藏，实现单例模式
    }

  static Meta getInstance(Integer code){
        Meta meta = new Meta();
        meta.code=code;
        meta.token= RequestUtil.getSessionToken();
        return meta;
    }

    static Meta getInstanceWithClearToken(Integer code, String msg){
        Meta meta = new Meta();
        meta.code=code;
        meta.token= null;
        meta.message=msg;
        return meta;
    }

    static Meta getInstance(Integer code, String msg){
        Meta meta = new Meta();
        meta.code=code;
        meta.token= RequestUtil.getSessionToken();
        meta.message=msg;
        return meta;
    }

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String getToken() {
        return token;
    }
}

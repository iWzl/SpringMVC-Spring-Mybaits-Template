package com.upuphub.template.util.http.message;

import com.upuphub.template.enums.ResultEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 王志立
 * @Project: basic
 * @Package:cc.wangzl.bean
 * @date 2018/9/18 20:12
 * @description 通用json返回包装类
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Msg {
    private Meta meta;
    @JsonProperty
    private Object data;
    private Msg() {
        //隐藏构造器
    }


    /**
     * 1. 创建Msg对象
     * 2. 组装Meta信息
     * 3. 为了防止脏读，放弃采用和单例的模式
     * 4. 成功: 无返回信息的成功返回/有返回信息的成功返回/带返回信息及其他数据的成功返回/无返回信息带其他数据的成功返回
     * 5. 失败: 无返回信息默认失败码的失败返回/无返回信息带异常码的失败返回/带返回信息带异常码的失败返回/带返回信息带异常码及返回数据的失败返回
     **/
    public static Msg success() {
        Msg result = new Msg();
        result.meta = Meta.getInstance(ResultEnum.SUCCESS.value());
        result.data = null;
        return result;
    }

    public static Msg success(String msg) {
        Msg result = new Msg();
        result.meta = Meta.getInstance(ResultEnum.SUCCESS.value(), msg);
        result.data = null;
        return result;
    }

    public static Msg success(String msg, Object obj) {
        Msg result = new Msg();
        result.meta = Meta.getInstance(ResultEnum.SUCCESS.value(), msg);
        result.data = obj;
        return result;
    }

    public static Msg success(Object obj) {
        Msg result = new Msg();
        result.meta = Meta.getInstance(ResultEnum.SUCCESS.value());
        result.data = obj;
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.meta = Meta.getInstance(ResultEnum.NOT_ACCEPTABLE.value());
        result.data = null;
        return result;
    }

    public static Msg fail(ResultEnum resultEnum) {
        Msg result = new Msg();
        result.meta = Meta.getInstance(resultEnum.value());
        result.data = null;
        return result;
    }

    public static Msg fail(ResultEnum resultEnum, String msg) {
        Msg result = new Msg();
        result.meta = Meta.getInstance(resultEnum.value(), msg);
        result.data = null;
        return result;
    }

    public static Msg fail(ResultEnum resultEnum, String msg,boolean clearToken) {
        Msg result = new Msg();
        if(clearToken){
            result.meta = Meta.getInstanceWithClearToken(resultEnum.value(), msg);
        }
        else {
            result.meta = Meta.getInstance(resultEnum.value(), msg);
        }
        result.data = null;
        return result;
    }

    public static Msg fail(ResultEnum resultEnum, String msg, Object object) {
        Msg result = new Msg();
        result.meta = Meta.getInstance(resultEnum.value(), msg);
        result.data = object;
        return result;
    }

    public Meta getMeta() {
        return meta;
    }
    public Object getData() {
        return data;
    }
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}



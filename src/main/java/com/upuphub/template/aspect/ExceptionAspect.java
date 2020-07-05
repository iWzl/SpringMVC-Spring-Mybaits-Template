package com.upuphub.template.aspect;

import com.upuphub.template.enums.ResultEnum;
import com.upuphub.template.util.http.message.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ValidationException;

/**
 * @author 王志立
 * @Project: basic
 * @Package:cc.wangzl.aspect
 * @date 2018/9/18 16:25
 * @description 统一异常处理，异常切面
 **/
@ControllerAdvice
@ResponseBody
public class ExceptionAspect {
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 400异常
     * NoHandlerFoundException 需要Servlet API支持
     * 客户端请求的语法错误，服务器无法理解
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Msg handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        log.error("不能正确读取JSON数据", e);
        return Msg.fail(ResultEnum.BAD_REQUEST, "不能正确读取JSON数据");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Msg handleValidationException(MethodArgumentNotValidException e) {
        log.error("方法参数验证失败", e);
        return Msg.fail(ResultEnum.BAD_REQUEST, "方法参数验证失败");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Msg handleValidationException(ValidationException e) {
        log.error("参数验证失败", e);
        return Msg.fail(ResultEnum.BAD_REQUEST, "参数校验失败");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Msg handlerNotFoundException(NoHandlerFoundException e) {
        log.error("请求的资源不可用", e);
        return Msg.fail(ResultEnum.NOT_FOUND, "请求的资源不可用");
    }

    /**
     * 405异常
     * 需要Servlet API支持
     * 客户端请求中的方法被禁止
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Msg handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        log.error("请求方法类型不支持", e);
        return Msg.fail(ResultEnum.METHOD_NOT_ALLOWED, "请求方法类型不支持");
    }


    /**
     * 415 异常
     * 需要Servlet API支持
     * 服务器无法处理请求附带的媒体格式
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public Msg handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("内容类型不支持", e);
        return Msg.fail(ResultEnum.UNSUPPORTED_MEDIA_TYPE, "内容类型不支持");
    }


    /**
     * 500 异常
     * 最大的异常处理
     * 服务器内部错误，无法完成请求
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Msg handleException(Exception e) {
        log.error("网络服务器异常", e);
        return Msg.fail(ResultEnum.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}

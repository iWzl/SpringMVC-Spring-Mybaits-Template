package com.upuphub.template.controller;

import com.upuphub.template.annotation.HttpStatus;
import com.upuphub.template.annotation.Security;
import com.upuphub.template.enums.RoleEnum;
import com.upuphub.template.util.http.message.Msg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: basic
 * @description: 测试控制器
 * @author: 王志立
 * @create: 2018-12-13 17:16
 **/

@RestController
@RequestMapping("demo")
public class DemoController {


    @HttpStatus
    @GetMapping("/token")
    @Security(roles = RoleEnum.LOG,createToken = true,checkToken = false)
    public Msg exceptionTest() {
        return Msg.success("测试请求","测试数据");
    }



    @HttpStatus
    @GetMapping("/token1")
    @Security(roles = RoleEnum.LOG)
    public Msg exceptionTest1() {
        return Msg.success("测试请求","测试数据");
    }
}

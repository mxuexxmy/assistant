package xyz.mxue.assistant.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mxuexxmy
 * @ClassName LoginController
 * @Description TODO
 * @Date 10/21/2020 11:31 PM
 * @Version 1.0
 **/
@Api(tags = "登录模块API")
@RestController
@RequestMapping("assistant")
public class LoginController {

    @ApiOperation("测试")
    @GetMapping
    public String test() {
        return "欢迎来到学委助手";
    }



}

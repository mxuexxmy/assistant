package xyz.mxue.assistant.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import xyz.mxue.assistant.entity.User;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.service.UserService;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/assistant/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param user 用户学习
     * @return Result<String>
     */
    @PostMapping("/register")
    public Result<String> register(User user) {

        return Result.succeed("注册成功");
    }

}


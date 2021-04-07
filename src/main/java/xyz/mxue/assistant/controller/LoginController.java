package xyz.mxue.assistant.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.assistant.commons.constant.ResultCode;
import xyz.mxue.assistant.commons.enumeration.UserStatusEnum;
import xyz.mxue.assistant.commons.utils.RegexUtils;
import xyz.mxue.assistant.entity.User;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.service.UserService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author mxuexxmy
 * @ClassName LoginController
 * @Description TODO
 * @Date 10/21/2020 11:31 PM
 * @Version 1.0
 **/
@Controller
@RequestMapping
public class LoginController {

    private String prefix = "pages";

    @Resource
    private UserService userService;

    /**
     * 登录页
     *
     * @return 页面
     */
    @GetMapping
    public String index() {
        return prefix + "/login";
    }

    /**
     * 注册页面
     *
     * @return String
     */
    @GetMapping("/register")
    public String register() {
        return prefix + "/register";
    }

    /**
     * 忘记密码页面
     *
     * @return String
     */
    @GetMapping("/forget")
    public String forget() {
        return prefix + "/forget";
    }

    /**
     * 邮箱注册页面
     *
     * @return String
     */
    @GetMapping("/register-email")
    public String registerEmail() {
        return prefix + "/register-email";
    }

    /**
     * 登录验证
     *
     * @param userInfo 用户信息
     * @param password 密码
     * @param remember 是否记住我
     * @return 返回登录信息
     */
    @PostMapping("/login")
    @ResponseBody
    public Result<String> login(String userInfo, String password, String remember) {
        String email = new String();
        if (RegexUtils.checkEmail(userInfo)) {
            email = userInfo;
        }

        // 如果不是手机号和邮箱的话返回
        if (!RegexUtils.checkPhone(userInfo) && !RegexUtils.checkEmail(userInfo)) {
            return Result.failed(ResultCode.USER_ERROR_A0402.getMessage());
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(email), "email", email)
                .eq("phone", userInfo);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            return StrUtil.isNotBlank(email) ? Result.failed("邮箱不存在！") : Result.failed("手机号不存在！");
        } else {

            if (user.getStatus().equals(UserStatusEnum.FREEZE.getValue())) {
                return Result.failed("你的账号被冻结, 请联系管理员！");
            }

            if (user.getStatus().equals(UserStatusEnum.DELETE.getValue())) {
                return Result.failed("你的账号被删除，请联系管理员！");
            }

            // 明文密码加密
            String md5Password = SecureUtil.md5(password);
            // 判断密码是否相等
            if (md5Password.equals(user.getPassword())) {
                user.setLastLoginTime(new Date());
                userService.updateById(user);
                if (StrUtil.isNotBlank(remember)) {
                    StpUtil.setLoginId(user.getId(), true);
                } else {
                    StpUtil.setLoginId(user.getId(), false);
                }
                return Result.succeed("登录成功!");
            }
        }
        return Result.failed("密码错误!");
    }

    /**
     * 退出
     *
     * @return Result<String>
     */
    @GetMapping("logout")
    @ResponseBody
    public Result<String> logout() {
        StpUtil.logout();
        return Result.succeed("退出登录成功，正在退出！");
    }

}

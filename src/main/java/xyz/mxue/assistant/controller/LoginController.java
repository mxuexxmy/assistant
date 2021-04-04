package xyz.mxue.assistant.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.assistant.commons.constant.ConstantUtils;
import xyz.mxue.assistant.commons.constant.ResultCode;
import xyz.mxue.assistant.commons.utils.RegexUtils;
import xyz.mxue.assistant.entity.User;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author mxuexxmy
 * @ClassName LoginController
 * @Description TODO
 * @Date 10/21/2020 11:31 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(String studentInfo, String password, String remember, HttpServletRequest request) {
        String email = new String();
        if (RegexUtils.checkEmail(studentInfo)) {
            email = studentInfo;
        }

        // 如果不是手机号和邮箱的话返回
        if (!RegexUtils.checkPhone(studentInfo) && !RegexUtils.checkEmail(studentInfo)) {
             return Result.failed(ResultCode.USER_ERROR_A0402.getMessage());
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(email), "email", email)
                .eq("phone", studentInfo);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            return StrUtil.isNotBlank(email) ? Result.failed("邮箱不存在！") : Result.failed("手机号不存在！");
        } else {
            // 明文密码加密
            String md5Password =  SecureUtil.md5(password);
            // 判断密码是否相等
            if (md5Password.equals(user.getPassword())) {
                request.getSession().setAttribute(ConstantUtils.SESSION_USER, user);
                if (StrUtil.isNotBlank(remember)) {
                    StpUtil.setLoginId(user.getId(),true);
                } else {
                    StpUtil.setLoginId(user.getId(), false);
                }
                return Result.succeed("登录成功!");
            }
        }
        return Result.failed("密码错误!");
    }

    @GetMapping("logout")
    public Result<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        StpUtil.logout();
        return Result.succeed("退出登录成功，正在退出！");
    }

}

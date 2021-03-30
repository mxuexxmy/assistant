package xyz.mxue.assistant.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.assistant.commons.constant.ConstantUtils;
import xyz.mxue.assistant.commons.constant.ResultCode;
import xyz.mxue.assistant.commons.utils.RegexUtils;
import xyz.mxue.assistant.entity.Student;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.service.StudentService;

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
    private StudentService studentService;

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

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(email), "email", email)
                .eq("phone", studentInfo);
        Student student = studentService.getOne(queryWrapper);
        if (student == null) {
            return StrUtil.isNotBlank(email) ? Result.failed("邮箱不存在！") : Result.failed("手机号不存在！");
        } else {
            // 明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            // 判断密码是否相等
            if (md5Password.equals(student.getPassword())) {
                request.getSession().setAttribute(ConstantUtils.SESSION_USER, student);
                if (StrUtil.isNotBlank(remember)) {
                    StpUtil.setLoginId(student.getId(),true);
                } else {
                    StpUtil.setLoginId(student.getId(), false);
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

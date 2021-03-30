package xyz.mxue.assistant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.assistant.commons.constant.ConstantUtils;
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
    public Result<String> login(HttpServletRequest httpServletRequest) {
        System.out.println(httpServletRequest);
//        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("student_id", studentId);
//        Student student = studentService.getOne(queryWrapper);
//        if (student == null) {
//            return Result.failed("学号不存在");
//        } else {
//            // 明文密码加密
//            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
//            // 判断密码是否相等
//            if (md5Password.equals(student.getPassword())) {
//                httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, student);
//                return Result.succeed("登录成功");
//            }
//
//            return Result.failed("密码错误");
        return Result.succeed("登录成功！");
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return "login";
    }



}

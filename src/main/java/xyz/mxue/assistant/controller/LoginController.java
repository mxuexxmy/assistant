package xyz.mxue.assistant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.mxue.assistant.commons.ConstantUtils;
import xyz.mxue.assistant.entity.Student;
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
@Controller
public class LoginController {

    @Resource
    private StudentService studentService;

    @PostMapping("/login")
    public String login(String studentId, String password,
                        HttpServletRequest httpServletRequest, ModelMap map) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId);
        Student student = studentService.getOne(queryWrapper);

        if (student == null) {
            map.put("msg", "学号不存在！");
            return "login";
        } else {
            // 明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            // 判断密码是否相等
            if (md5Password.equals(student.getPassword())) {
                httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, student);
                return "redirect:/index";
            }

            map.put("msg", "密码错误！");
            return "login";
        }
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest httpServletRequest, ModelMap map){
        httpServletRequest.getSession().invalidate();
        return "login";
    }



}

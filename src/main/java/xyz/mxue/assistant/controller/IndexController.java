package xyz.mxue.assistant.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mxuexxmy
 * @ClassName IndexController
 * @Description TODO
 * @Date 10/22/2020 12:31 AM
 * @Version 1.0
 **/
@Controller
@RequestMapping
public class IndexController {

    private String prefix = "pages";

    // 登录页
    @GetMapping
    public String index() {
        return prefix + "/login";
    }

    // 首页
    @GetMapping("/main")
    public String indexSHow() {
        return "index";
    }

    // 控制台页
    @GetMapping("/console")
    public String console() {
        return prefix + "/console";
    }

    // 系统设置页面
    @GetMapping("/system/setting")
    public String systemSetting() {
        return prefix + "/system/setting";
    }

    // 用户信息页面
    @GetMapping("/user-info")
    public String userInfo() {
        return prefix + "/member/user-info";
    }

    // 用户信息修改页面
    @GetMapping("/user-update")
    public String userUpdate() {
        return prefix + "/member/user-update";
    }

    // 修改密码页面
    @GetMapping("/user-password")
    public String updatePassword() {
        return prefix + "/member/user-pwd";
    }

    // 学生列表页面
    @GetMapping("/student-list")
    public String studentList() {
        return prefix + "/class/student-list";
    }

    // 皮肤动画
    @GetMapping("/alert-skin")
    public String alertSkin() {
        return prefix + "/system/alertSkin";
    }

    // 注册页面
    @GetMapping("/register")
    public String register() {
        return prefix + "/register";
    }

    // 忘记密码页面
    @GetMapping("/forget")
    public String forget() {
        return prefix + "/forget";
    }

    // 邮箱注册页面
    @GetMapping("/register-email")
    public String registerEmail() {
        return prefix + "/register-email";
    }


}

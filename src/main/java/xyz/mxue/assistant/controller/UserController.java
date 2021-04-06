package xyz.mxue.assistant.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import xyz.mxue.assistant.entity.User;
import xyz.mxue.assistant.model.PageResult;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.service.UserService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-04
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private String prefix = "pages";

    @Resource
    private UserService userService;

    /**
     * 系统用户列表页面
     *
     * @return String
     */
    @GetMapping("/list-page")
    public String systemUser() {
        return prefix + "/user/list";
    }

    /**
     * 非管理员控制台页-及学生控制台
     *
     * @return String
     */
    @GetMapping("/console")
    public String console() {
        return prefix + "/console";
    }

    /**
     * 管理员控制台
     *
     * @param map 返回信息集合
     * @return String
     */
    @GetMapping("/console-admin")
    public String consoleAdmin(ModelMap map) {
        Date nowDate = new Date();
        // 系统总人数
        map.put("systemCount", userService.count());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("create_time", DateUtil.beginOfDay(nowDate), nowDate);
        // 今日新增人数
        map.put("dayCount", userService.count(queryWrapper));
        // 今天登录人数
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.between("last_login_time", DateUtil.beginOfDay(nowDate), nowDate);
        map.put("dayLoginCount", userService.count(queryWrapper1));
        return prefix + "/console-admin";
    }

    /**
     * 管理员用户信息页面
     *
     * @param map 返回集合
     * @return String
     */
    @GetMapping("/info-admin")
    public String userInfo(ModelMap map) {
        User user = userService.getById((Serializable) StpUtil.getLoginId());
        map.put("user", user);
        return prefix + "/member/user-info-admin";
    }

    /**
     * 用户注册
     *
     * @param user 用户学习
     * @return Result<String>
     */
    @PostMapping("/register")
    @ResponseBody
    public Result<String> register(User user) {
        return Result.succeed("注册成功");
    }

    /**
     * 修改密码页面
     *
     * @return String
     */
    @GetMapping("/password")
    public String updatePassword(ModelMap map) {
        User user = userService.getById((Serializable) StpUtil.getLoginId());
        map.put("user", user);
        return prefix + "/member/user-pwd";
    }

    /**
     * 系统用户列表
     *
     * @param current 当前页面
     * @param size    页面条数
     * @param user    用户信息-搜索
     * @return PageResult<User>
     */
    @GetMapping("/list")
    @ResponseBody
    public PageResult<User> list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer current,
                                 @RequestParam(value = "limit", required = false, defaultValue = "10") Integer size,
                                 User user) {
        Page<User> page = new Page<>(current, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(user.getNickName()), "nick_name", user.getNickName())
                .like(StrUtil.isNotBlank(user.getPhone()), "phone", user.getPhone())
                .like(StrUtil.isNotBlank(user.getEmail()), "email", user.getEmail())
                .eq(Objects.nonNull(user.getAdminType()), "admin_type", user.getAdminType())
                .eq(Objects.nonNull(user.getSex()), "sex", user.getSex())
                .eq(Objects.nonNull(user.getStatus()), "status", user.getStatus());
        return PageResult.succeed(userService.page(page, queryWrapper));
    }

}


package xyz.mxue.assistant.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import xyz.mxue.assistant.commons.enumeration.StudentTypeEnum;
import xyz.mxue.assistant.commons.enumeration.UserStatusEnum;
import xyz.mxue.assistant.entity.User;
import xyz.mxue.assistant.model.PageResult;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.model.vo.UserAndStudentInfoVO;
import xyz.mxue.assistant.service.UserService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

    private final String prefix = "/pages";

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
    @GetMapping("/info")
    public String userInfo(ModelMap map) {
        User user = userService.getById((Serializable) StpUtil.getLoginId());
        map.put("user", user);
        return prefix + "/member/info";
    }

    /**
     * 学生个人信息中心
     *
     * @param map 规划集合
     * @return String
     */
    @GetMapping("/student-info")
    public String studentInfo(ModelMap map) {
        UserAndStudentInfoVO userAndStudentInfoVO = userService.getUserAndStudentType((Serializable) StpUtil.getLoginId());
        if (Objects.isNull(userAndStudentInfoVO.getStudentType()))
            userAndStudentInfoVO.setStudentType(StudentTypeEnum.GENERAL_STUDENT.getValue());
        map.put("user", userAndStudentInfoVO);
        return prefix + "/member/student-info";
    }

    /**
     * 修改个人信息-保存
     *
     * @param user 用户信息
     * @return Result<String>
     */
    @PostMapping("/update-user-info")
    @ResponseBody
    public Result<String> updateUserInfo(User user) {
        boolean b = userService.updateById(user);
        return b ? Result.succeed("修改成功！") : Result.failed("修改失败！");
    }

    /**
     * 修改密码页面
     *
     * @return String
     */
    @GetMapping("/password")
    public String password(ModelMap map) {
        User user = userService.getById((Serializable) StpUtil.getLoginId());
        map.put("user", user);
        return prefix + "/member/pwd";
    }

    /**
     * 添加用户页面
     *
     * @return String
     */
    @GetMapping("/add-user-page")
    public String addUserPage() {
        return prefix + "/user/add";
    }

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return Result<String>
     */
    @PostMapping("/save-user-info")
    @ResponseBody
    public Result<String> savaUserInfo(User user) {
        user.setStatus(UserStatusEnum.NORMAL.getValue());
        user.setPassword(SecureUtil.md5(user.getPassword()));
        boolean save = userService.save(user);
        return save ? Result.succeed("添加用户成功！") : Result.failed("添加用户失败!");
    }

    /**
     * 修改用户信息页面
     *
     * @param id 用户id
     * @return String
     */
    @GetMapping("/update-page/{id}")
    public String editUserInfo(@PathVariable Long id, ModelMap map) {
        map.put("userInfo", userService.getById(id));
        return prefix + "/user/update";
    }

    /**
     * 保存修改的用户信息
     *
     * @param user 用户信息
     * @return Result<String>
     */
    @PostMapping("/save-user-edit")
    @ResponseBody
    public Result<String> saveUserEdit(User user) {
        User originUserInfo = userService.getById(user.getId());
        if (!originUserInfo.getPassword().equals(user.getPassword())) {
            user.setPassword(SecureUtil.md5(user.getPassword()));
        }
        user.setUpdateTime(new Date());
        boolean b = userService.updateById(user);
        return b ? Result.succeed("用户信息修改成功！") : Result.failed("用户信息修改失败！");
    }

    /**
     * 通过 ID 删除用户
     *
     * @param id 用户id
     * @return Result<String>
     */
    @GetMapping("/delete/{id}")
    @ResponseBody
    public Result<String> deleteUserById(@PathVariable String id) {
        User user = userService.getById(id);
        user.setStatus(UserStatusEnum.DELETE.getValue());
        boolean b = userService.updateById(user);
        return b ? Result.succeed("用户删除成功！") : Result.failed("用户已删除！");
    }

    /**
     * 批量删除
     *
     * @param idsStr id集合
     * @return Result<String>
     */
    @PostMapping("/batch-delete")
    @ResponseBody
    public Result<String> deleteUserByIds(String idsStr) {
        List<User> userList = userService.listByIds(Arrays.asList(idsStr.split(",")));
        for (User user : userList) {
            user.setStatus(UserStatusEnum.DELETE.getValue());
        }
        boolean b = userService.updateBatchById(userList);
        return b ? Result.succeed("批量删除成功！") : Result.failed("批量删除失败！");
    }

    /**
     * 批量正常
     *
     * @param idsStr id集合
     * @return Result<String>
     */
    @PostMapping("/batch-normal")
    @ResponseBody
    public Result<String> batchUserNormalByIds(String idsStr) {
        List<User> userList = userService.listByIds(Arrays.asList(idsStr.split(",")));
        for (User user : userList) {
            user.setStatus(UserStatusEnum.NORMAL.getValue());
        }
        boolean b = userService.updateBatchById(userList);
        return b ? Result.succeed("批量正常成功！") : Result.failed("批量正常失败！");

    }

    /**
     * 批量 冻结
     *
     * @param idsStr id集合
     * @return Result<String>
     */
    @PostMapping("/batch-freeze")
    @ResponseBody
    public Result<String> batchUserFreezeByIds(String idsStr) {
        List<User> userList = userService.listByIds(Arrays.asList(idsStr.split(",")));
        for (User user : userList) {
            user.setStatus(UserStatusEnum.FREEZE.getValue());
        }
        boolean b = userService.updateBatchById(userList);
        return b ? Result.succeed("批量冻结成功！") : Result.failed("批量冻结失败！");
    }


    /**
     * 修改密码保存
     *
     * @return Result<String>
     */
    @PostMapping("/update-password")
    @ResponseBody
    public Result<String> updatePassword(Long id, String oldPwd, String pwd) {
        User user = userService.getById(id);
        if (user == null) {
            return Result.failed("用户不存在!");
        }
        // 判断旧密码
        if (!user.getPassword().equals(SecureUtil.md5(oldPwd))) {
            return Result.failed("输入的旧密码错误！");
        }
        // 修改密码
        user.setPassword(SecureUtil.md5(pwd));
        boolean b = userService.updateById(user);
        if (b) {
            StpUtil.logout();
        }
        return b ? Result.succeed("密码修改成功！") : Result.failed("密码修改失败！");
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
                .eq(Objects.nonNull(user.getStatus()), "status", user.getStatus())
                .orderByDesc("update_time");
        return PageResult.succeed(userService.page(page, queryWrapper));
    }

}


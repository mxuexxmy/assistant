package xyz.mxue.assistant.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.mxue.assistant.entity.User;
import xyz.mxue.assistant.service.SysMachineService;
import xyz.mxue.assistant.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * 页面控制页
 *
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

    @Resource
    private UserService userService;

    @Resource
    private SysMachineService sysMachineService;

    /**
     * 首页
     *
     * @param map 返回信息集合
     * @return 页面
     */
    @GetMapping("/main")
    public String indexSHow(ModelMap map) {
        User user = userService.getById((Serializable) StpUtil.getLoginId());
        map.put("user", user);
        return "index";
    }

    /**
     * 系统设置页面
     *
     * @return String
     */
    @GetMapping("/system/setting")
    public String systemSetting() {
        return prefix + "/system/setting";
    }


    /**
     * 皮肤动画
     *
     * @return String
     */
    @GetMapping("/alert-skin")
    public String alertSkin() {
        return prefix + "/system/alertSkin";
    }

    /**
     * 系统信息页面
     *
     * @return String
     */
    @GetMapping("/system/info")
    public String systemInfo(ModelMap map) {
        map.put("systemInfo", sysMachineService.query());
        return prefix + "/system/system-info";
    }
}

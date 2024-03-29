package xyz.mxue.assistant.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.mxue.assistant.commons.enumeration.AdminTypeEnum;
import xyz.mxue.assistant.entity.User;
import xyz.mxue.assistant.service.UserService;

import javax.annotation.Resource;
import java.io.Serializable;


/**
 * @author mxuexxmy
 * @date 4/4/2021$ 8:00 PM$
 */
@RestController
@RequestMapping("navs")
public class NavsController {

    private String navs;

    @Resource
    private UserService userService;

    @GetMapping
    public Object navs() {
        User user = userService.getById((Serializable) StpUtil.getLoginId());
        // 超级管理员
        if (user.getAdminType().equals(AdminTypeEnum.SUPER_ADMIN.getValue())) {
            return admin();
        }

        return assistant();
    }

    /**
     * 管理员菜单
     *
     * @return string
     */
    private String admin() {
        navs = "[\n" +
                "    {\n" +
                "        \"title\": \"控制台\",\n" +
                "        \"href\": \"/user/console-admin\",\n" +
                "        \"fontFamily\": \"ok-icon\",\n" +
                "        \"icon\": \"&#xe654;\",\n" +
                "        \"spread\": true,\n" +
                "        \"isCheck\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"title\": \"用户管理\",\n" +
                "        \"href\": \"\",\n" +
                "        \"icon\": \"&#xe612;\",\n" +
                "        \"spread\": false,\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"title\": \"用户列表\",\n" +
                "                \"href\": \"/user/list-page\",\n" +
                "                \"icon\": \"&#xe612;\",\n" +
                "                \"spread\": false\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"title\": \"系统监控\",\n" +
                "        \"href\": \"\",\n" +
                "        \"fontFamily\": \"ok-icon\",\n" +
                "        \"icon\": \"&#xe68a;\",\n" +
                "        \"spread\": false,\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"title\": \"服务监控\",\n" +
                "                \"href\": \"/system/info\",\n" +
                "                \"icon\": \"&#xe62e;\",\n" +
                "                \"spread\": false\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        return navs;
    }

    /**
     * 非管理员菜单
     *
     * @return String
     */
    private String assistant() {
        navs = "[\n" +
                "    {\n" +
                "        \"title\": \"控制台\",\n" +
                "        \"href\": \"pages/console.html\",\n" +
                "        \"fontFamily\": \"ok-icon\",\n" +
                "        \"icon\": \"&#xe654;\",\n" +
                "        \"spread\": true,\n" +
                "        \"isCheck\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"title\": \"班级作业\",\n" +
                "        \"href\": \"/work-info/index\",\n" +
                "        \"icon\": \"&#xe665;\",\n" +
                "        \"spread\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"title\": \"班级文件\",\n" +
                "        \"href\": \"/class-file/index\",\n" +
                "        \"icon\": \"&#xe665;\",\n" +
                "        \"spread\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"title\": \"班级管理\",\n" +
                "        \"href\": \"/class-info/list\",\n" +
                "        \"icon\": \"&#xe665;\",\n" +
                "        \"spread\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"title\": \"成员管理\",\n" +
                "        \"href\": \"/student/list-page\",\n" +
                "        \"icon\": \"&#xe612;\",\n" +
                "        \"spread\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"title\": \"班级通知\",\n" +
                "        \"href\": \"\",\n" +
                "        \"fontFamily\": \"ok-icon\",\n" +
                "        \"icon\": \"&#xe68a;\",\n" +
                "        \"spread\": false,\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"title\": \"班级公告\",\n" +
                "                \"href\": \"/class-notice/index\",\n" +
                "                \"icon\": \"&#xe62e;\",\n" +
                "                \"spread\": false\n" +
                "            },\n" +
                "            {\n" +
                "                \"title\": \"作业提醒\",\n" +
                "                \"href\": \"/work-notice/index\",\n" +
                "                \"icon\": \"&#xe62e;\",\n" +
                "                \"spread\": false\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        return navs;
    }


}

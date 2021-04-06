package xyz.mxue.assistant.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.mxue.assistant.commons.enumeration.AdminTypeEnum;
import xyz.mxue.assistant.commons.enumeration.StudentTypeEnum;
import xyz.mxue.assistant.entity.Student;
import xyz.mxue.assistant.entity.User;
import xyz.mxue.assistant.service.StudentService;
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

    @Resource
    private UserService userService;

    @Resource
    private StudentService studentService;

    @GetMapping
    public Object navs() {
        User user = userService.getById((Serializable) StpUtil.getLoginId());
        // 超级管理员
        if (user.getAdminType().equals(AdminTypeEnum.SUPER_ADMIN.getValue())) {
            return admin();
        }
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        Student student = studentService.getOne(queryWrapper);
        // 学委
        if (student.getStudentType().equals(StudentTypeEnum.ACADEMIC_COMMITTEE.getValue())) {
            return academicCommittee();
        }

        // 学委助理
        if (student.getStudentType().equals(StudentTypeEnum.ACADEMIC_COMMITTEE_ASSISTANT.getValue())) {
            return assistant();
        }

        // 普通学生
        return generalStudent();
    }

    /**
     * 管理员菜单
     *
     * @return string
     */
    private String admin() {
        String navs = new String();
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
                "        \"icon\": \"&#xe66f;\",\n" +
                "        \"spread\": false,\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"title\": \"用户列表\",\n" +
                "                \"href\": \"/user/list-page\",\n" +
                "                \"icon\": \"&#xe62e;\",\n" +
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
     * 学委菜单
     *
     * @return String
     */
    private String academicCommittee() {
        String navs = new String();

        return navs;
    }

    /**
     * 学委助手菜单
     * @return String
     */
    private String assistant() {
        String navs = new String();

        return navs;
    }

    /**
     * 普通学生菜单
     * @return
     */
    private String generalStudent() {
        String navs = new String();

        return navs;
    }
}

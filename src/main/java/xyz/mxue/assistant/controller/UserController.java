package xyz.mxue.assistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.mxue.assistant.entity.Student;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mxuexxmy
 * @date 12/23/2020$ 3:24 PM$
 */
@Controller
@RequestMapping("user")
public class UserController {

    private String prefix = "user";

    @GetMapping("profile")
    public String profile(HttpServletRequest request, ModelMap map) {
        Student student  = (Student) request.getSession().getAttribute("student");
        map.put("student", student);
        return prefix + "/profile";
    }





}

package xyz.mxue.assistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mxuexxmy
 * @date 12/23/2020$ 3:24 PM$
 */
@Controller
@RequestMapping("user")
public class UserController {

    private String prefix = "user";

    @GetMapping("register")
    public String userRegister() {
        return "user/register";
    }


}

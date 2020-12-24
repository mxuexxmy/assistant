package xyz.mxue.assistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mxuexxmy
 * @ClassName IndexController
 * @Description TODO
 * @Date 10/22/2020 12:31 AM
 * @Version 1.0
 **/
@Controller
public class IndexController {

    @GetMapping
    public String index() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String indexView() {
        return "index";
    }


}

package xyz.mxue.assistant.controller;

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

    @GetMapping
    public String index() {
        return prefix + "/login";
    }

    @GetMapping("main")
    public String indexSHow() {
        return "index";
    }

    @GetMapping("/console")
    public String console() {
        return prefix + "/console";
    }

}

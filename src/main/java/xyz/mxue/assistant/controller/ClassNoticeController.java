package xyz.mxue.assistant.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 班级通知 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-04
 */
@Controller
@RequestMapping("/class-notice")
public class ClassNoticeController {

    private final String prefix = "/pages/class-notice";

    /**
     * 班级通知页面
     *
     * @return String
     */
    @GetMapping("/index")
    public String index() {
        return prefix + "/index";
    }

}


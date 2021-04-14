package xyz.mxue.assistant.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 作业提醒 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Controller
@RequestMapping("/work-notice")
public class WorkNoticeController {

    private final String prefix = "/pages/work-notice";

    /**
     * 作业提醒页面
     *
     * @return String
     */
    @GetMapping("/index")
    public String index() {
        return prefix + "/index";
    }

}


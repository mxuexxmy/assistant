package xyz.mxue.assistant.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 作业提交记录 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Controller
@RequestMapping("/work-submit")
public class WorkSubmitController {

    private final String prefix = "/pages/work-submit";

    /**
     * 作业提交页面
     *
     * @return String
     */
    @GetMapping("/index")
    private String index() {
        return prefix + "/index";
    }

    /**
     * 作业提交详情
     *
     * @param id 作业ID
     * @return String
     */
    @GetMapping("/detail/{id}")
    public String submitWorkDetail(@PathVariable String id) {
        return null;
    }
}


package xyz.mxue.assistant.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 学生 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    private String prefix = "pages";

    // 学生列表页面
    @GetMapping("/student-list")
    public String studentList() {
        return prefix + "/class/student-list";
    }

}


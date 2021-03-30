package xyz.mxue.assistant.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.mxue.assistant.entity.Student;
import xyz.mxue.assistant.model.PageResult;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.service.StudentService;

import javax.annotation.Resource;

/**
 * <p>
 * 学生 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/assistant/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/list")
    public PageResult<Student> listStudent(@RequestParam(value = "current", required = false, defaultValue = "1") Integer curr,
                                  @RequestParam(value = "size", required = false, defaultValue = "10") Integer limit) {
        Page<Student> page = new Page<>(curr, limit);
        return PageResult.succeed(studentService.page(page, null));
    }

}


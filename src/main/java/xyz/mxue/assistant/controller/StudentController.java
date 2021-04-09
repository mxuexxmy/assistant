package xyz.mxue.assistant.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.mxue.assistant.entity.Student;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.service.StudentService;

import javax.annotation.Resource;

/**
 * <p>
 * 学生 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 保存修改的学生信息
     *
     * @param student 学生信息
     * @return Result<String>
     */
    @PostMapping("/save-edit")
    @ResponseBody
    public Result<String> saveEditStudentInfo(Student student) {
        boolean b = studentService.updateById(student);
        return b == true ? Result.succeed("修改学生信息成功！") : Result.failed("修改学生信息失败！");
    }

}


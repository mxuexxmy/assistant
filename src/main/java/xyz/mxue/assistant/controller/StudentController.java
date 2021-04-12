package xyz.mxue.assistant.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import xyz.mxue.assistant.commons.enumeration.StudentTypeEnum;
import xyz.mxue.assistant.entity.Student;
import xyz.mxue.assistant.model.PageResult;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.service.StudentService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    private final String prefix = "/pages/student";

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

    /**
     * 班级成员信息列表页面
     *
     * @return String
     */
    @GetMapping("/list-page")
    public String listPage(ModelMap map) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
        map.put("student", studentService.getOne(queryWrapper));
        return prefix + "/list";
    }

    /**
     * 批量设置管理员
     *
     * @param idsStr id集合
     * @return Result<String>
     */
    @PostMapping("/batch-admin")
    @ResponseBody
    public Result<String> batchAdmin(String idsStr) {
        boolean b = studentService.updateBatchById(getBatchStudent(idsStr, StudentTypeEnum.ACADEMIC_COMMITTEE.getValue(),
                StudentTypeEnum.ACADEMIC_COMMITTEE_ASSISTANT.getValue()));
        return b ? Result.succeed("批量设置管理员成功！") : Result.failed("批量设置管理员失败！");
    }

    /**
     * 批量取消管理
     *
     * @param idsStr id集合
     * @return Result<String>
     */
    @PostMapping("/batch-cancel")
    @ResponseBody
    public Result<String> batchCancel(String idsStr) {
        boolean b = studentService.updateBatchById(getBatchStudent(idsStr, StudentTypeEnum.ACADEMIC_COMMITTEE.getValue(),
                StudentTypeEnum.GENERAL_STUDENT.getValue()));
        return b ? Result.succeed("批量取消管理成功！！") : Result.failed("批量取消管理失败！");
    }

    /**
     * 批量得到学生信息
     *
     * @param idsStr            id集合
     * @param removeStudentType 排除学生角色
     * @param goalStudentType   得到的学生角色
     * @return List<Student>
     */
    private List<Student> getBatchStudent(String idsStr, Integer removeStudentType, Integer goalStudentType) {
        // 查询出学生列表
        List<Student> studentList = studentService.listByIds(Arrays.asList(idsStr.split(",")));
        // 排除学委
        List<Student> newStudentList = new ArrayList<>();
        for (Student student : studentList) {
            if (!student.getStudentType().equals(removeStudentType)) {
                student.setStudentType(goalStudentType);
                newStudentList.add(student);
            }
        }
        return newStudentList;
    }

    /**
     * 班级成员信息
     *
     * @param current 当前页
     * @param size    页面条数
     * @param student 学生信息
     * @return PageResult<Student>
     */
    @GetMapping("/list")
    @ResponseBody
    public PageResult<Student> list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer current,
                                    @RequestParam(value = "limit", required = false, defaultValue = "10") Integer size,
                                    Student student) {
        Page<Student> page = new Page<>(current, size);
        // 先查出学生信息
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
        Student student1 = studentService.getOne(queryWrapper);
        QueryWrapper<Student> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("class_id", student1.getClassId())
                .like(StrUtil.isNotBlank(student.getStudentName()), "student_name", student.getStudentName())
                .like(StrUtil.isNotBlank(student.getStudentNo()), "student_no", student.getStudentNo())
                .eq(Objects.nonNull(student.getStudentType()), "student_type", student.getStudentType());
        return PageResult.succeed(studentService.page(page, queryWrapper1));
    }
}


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
import xyz.mxue.assistant.entity.StudentClassRelated;
import xyz.mxue.assistant.model.PageResult;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.model.vo.StudentInfoVO;
import xyz.mxue.assistant.service.ClassInfoService;
import xyz.mxue.assistant.service.StudentClassRelatedService;
import xyz.mxue.assistant.service.StudentService;
import xyz.mxue.assistant.service.impl.StudentServiceImpl;

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

    @Resource
    private StudentClassRelatedService classRelatedService;

    @Resource
    private ClassInfoService classInfoService;

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
        map.put("studentType", studentService.getStudentType(StpUtil.getLoginIdAsLong()));
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
        boolean b = classRelatedService.updateBatchById(getBatchStudent(idsStr, StudentTypeEnum.ACADEMIC_COMMITTEE.getValue(),
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
        boolean b = classRelatedService.updateBatchById(getBatchStudent(idsStr, StudentTypeEnum.ACADEMIC_COMMITTEE.getValue(),
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
    private List<StudentClassRelated> getBatchStudent(String idsStr, Integer removeStudentType, Integer goalStudentType) {
        // 查询当前的班级ID
        Long classId = classInfoService.getClassIdByStudent(StpUtil.getLoginIdAsLong());
        // 查询学生列表
        List<Student> studentList = studentService.listByIds(Arrays.asList(idsStr.split(",")));
        List<StudentClassRelated> classRelatedList = new ArrayList<>();
        for (Student student : studentList) {
            QueryWrapper<StudentClassRelated> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_id", student.getId())
                    .eq("class_id", classId);
            classRelatedList.add(classRelatedService.getOne(queryWrapper));

        }

        List<StudentClassRelated> studentClassRelatedList = new ArrayList<>();
        for (StudentClassRelated classRelated : classRelatedList) {
            if (!classRelated.getStudentType().equals(removeStudentType)) {
                classRelated.setStudentType(goalStudentType);
                studentClassRelatedList.add(classRelated);
            }
        }
        return studentClassRelatedList;
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
    public PageResult<StudentInfoVO> list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer current,
                                          @RequestParam(value = "limit", required = false, defaultValue = "10") Integer size,
                                          StudentInfoVO student) {
        return studentService.queryStudentList(current, size, student);
    }
}


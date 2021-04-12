package xyz.mxue.assistant.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import xyz.mxue.assistant.commons.enumeration.ClassIsNowEnum;
import xyz.mxue.assistant.commons.enumeration.StudentTypeEnum;
import xyz.mxue.assistant.entity.ClassInfo;
import xyz.mxue.assistant.entity.Student;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.service.ClassInfoService;
import xyz.mxue.assistant.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 班级 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Controller
@RequestMapping("/class-info")
public class ClassInfoController {

    private final String prefix = "pages/class";

    @Resource
    private ClassInfoService classInfoService;

    @Resource
    private StudentService studentService;

    /**
     * 班级信息页面
     *
     * @param map
     * @return
     */
    @GetMapping("/info-page")
    public String studentInfoPage(ModelMap map) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
        Student student = studentService.getOne(queryWrapper);
        ClassInfo classInfo = null;
        if (student != null) {
            classInfo = classInfoService.getById(student.getClassId());
        }
        map.put("classInfo", classInfo);
        map.put("student", student);
        return prefix + "/info";
    }

    /**
     * 添加班级页面
     *
     * @return String
     */
    @GetMapping("/add-page")
    public String addClassPage() {
        return prefix + "/add";
    }

    /**
     * 加入班级页面
     *
     * @return String
     */
    @GetMapping("/join-page")
    public String joinClass() {
        return prefix + "/join";
    }

    /**
     * 创建班级
     *
     * @param classInfo 班级信息
     * @return Result<String>
     */
    @PostMapping("/save-new-class")
    @ResponseBody
    public Result<String> savaNewClass(ClassInfo classInfo) {
        boolean save = true;
        classInfo.setUserId(StpUtil.getLoginIdAsLong());
        save = classInfoService.save(classInfo);
        // 创建一个对应的学生账号
        Student student = new Student();
        student.setUserId(StpUtil.getLoginIdAsLong());
        student.setClassId(classInfo.getId());
        student.setStudentType(StudentTypeEnum.ACADEMIC_COMMITTEE.getValue());
        // 保存班级消息
        if (save) {
            // 更改之前班级状态
            changeClassIsNow(StpUtil.getLoginIdAsLong());
            save = studentService.save(student);
            // 在学生保存之后把学生ID加入 班级
            classInfo.setStudentId(student.getId());
            classInfoService.updateById(classInfo);
        }
        return save ? Result.succeed("创建班级成功！") : Result.failed("创建班级失败！");
    }

    /**
     * 更改班级状态
     *
     * @param loginIdAsLong 用户ID
     * @return boolean
     */
    private boolean changeClassIsNow(long loginIdAsLong) {
        boolean update = true;
        QueryWrapper<ClassInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", loginIdAsLong)
                .eq("is_now", ClassIsNowEnum.IS_NOW.getValue());
        ClassInfo classInfo = classInfoService.getOne(queryWrapper);
        if (classInfo != null) {
            update = classInfoService.updateById(classInfo);
        }
        return update;
    }

    /**
     * 加入班级
     *
     * @param classInfo 班级信息
     * @return Result<String>
     */
    @PostMapping("/save-join-class")
    @ResponseBody
    public Result<String> saveJoinClass(ClassInfo classInfo) {
        boolean save = false;
        QueryWrapper<ClassInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("grade", classInfo.getGrade())
                .eq("class_name", classInfo.getClassName())
                .eq("class_key", classInfo.getClassKey());
        ClassInfo classInfo1 = classInfoService.getOne(queryWrapper);
        if (classInfo1 != null) {
            Student student = new Student();
            student.setUserId(StpUtil.getLoginIdAsLong());
            student.setClassId(classInfo1.getId());
            student.setStudentType(StudentTypeEnum.GENERAL_STUDENT.getValue());
            // 保存当前加入的班级
            save = studentService.save(student);
        }

        return save ? Result.succeed("加入班级成功！") : Result.failed("班级不存在！");
    }

    /**
     * 编辑班级信息页面
     *
     * @param map 返回班级信息页面集合信息
     * @return String
     */
    @GetMapping("/edit-class-page")
    public String editClassInfoPage(ModelMap map) {
        QueryWrapper<ClassInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", StpUtil.getLoginIdAsLong())
                .eq("is_now", ClassIsNowEnum.IS_NOW.getValue());
        map.put("classInfo", classInfoService.getOne(queryWrapper));
        return prefix + "/edit";
    }

    /**
     * 编辑班级信息页面 通过ID
     *
     * @param id  班级ID
     * @param map 返回集合
     * @return String
     */
    @GetMapping("/edit-info-page-id/{id}")
    public String editClassInfoPageById(@PathVariable(value = "id") String id, ModelMap map) {
        map.put("classInfo", classInfoService.getById(id));
        return prefix + "/edit";
    }

    /**
     * 保存修改班级信息
     *
     * @param classInfo 班级信息
     * @return Result<String>
     */
    @PostMapping("/save-edit-info")
    @ResponseBody
    public Result<String> saveEditClassInfo(ClassInfo classInfo) {
        boolean b = classInfoService.updateById(classInfo);
        return b ? Result.succeed("修改班级信息成功！") : Result.failed("修改班级信息失败！");
    }

    /**
     * 切换班级页面
     *
     * @param map 返回信息集合
     * @return String
     */
    @GetMapping("/change-class-page")
    public String changeClassInfo(ModelMap map) {
        Long userId = StpUtil.getLoginIdAsLong();
        map.put("classList", classInfoService.getClassInfoList(userId, ClassIsNowEnum.DELETE.getValue()));
        return prefix + "/change";
    }

    /**
     * 删除班级
     *
     * @param id 班级ID
     * @return Result<String>
     */
    @GetMapping("/delete-class/{id}")
    public Result<String> deleteClass(@PathVariable(value = "id") Long id) {
        return null;
    }

    /**
     * 退出班级
     *
     * @param id 班级ID
     * @return Result<String>
     */
    @GetMapping("/delete-related/{id}")
    public Result<String> deleteClassRelated(@PathVariable(value = "id") Long id) {
        return null;
    }

    /**
     * @param map
     * @return String
     */
    @GetMapping("/list")
    public String classList(ModelMap map) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
        Student student = studentService.getOne(queryWrapper);
        List<ClassInfo> createClassList = classInfoService.getMyCreateClass(student.getId(), StpUtil.getLoginIdAsLong());
        List<ClassInfo> joinClassList = classInfoService.getMyJoinClass(student.getId(), StpUtil.getLoginIdAsLong());
        if (createClassList.isEmpty()) createClassList = null;
        if (joinClassList.isEmpty()) joinClassList = null;
        map.put("createClassList", createClassList);
        map.put("joinClassList", joinClassList);
        return prefix + "/list";
    }

}


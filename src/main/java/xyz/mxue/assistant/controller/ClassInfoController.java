package xyz.mxue.assistant.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import xyz.mxue.assistant.commons.enumeration.StudentTypeEnum;
import xyz.mxue.assistant.entity.ClassInfo;
import xyz.mxue.assistant.entity.Student;
import xyz.mxue.assistant.entity.StudentClassRelated;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.service.ClassInfoService;
import xyz.mxue.assistant.service.StudentClassRelatedService;
import xyz.mxue.assistant.service.StudentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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

    private final String prefix = "/pages/class";

    @Resource
    private ClassInfoService classInfoService;

    @Resource
    private StudentService studentService;

    @Resource
    private StudentClassRelatedService classRelatedService;

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
            classInfo = classInfoService.getClassInfoByRelatedId(student.getRelatedId());
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
        // 先去查询有没有学生账号，没有的话就创建一个
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
        Student student = studentService.getOne(queryWrapper);
        if (Objects.isNull(student)) {
            student = new Student();
            student.setUserId(StpUtil.getLoginIdAsLong());
            studentService.save(student);
        }

        classInfo.setStudentId(student.getId());
        classInfo.setUserId(StpUtil.getLoginIdAsLong());
        classInfoService.save(classInfo);
        // 创建一个学生班级关联
        StudentClassRelated studentClassRelated = new StudentClassRelated();
        studentClassRelated.setClassId(classInfo.getId());
        studentClassRelated.setStudentId(student.getId());
        studentClassRelated.setStudentType(StudentTypeEnum.ACADEMIC_COMMITTEE.getValue());
        classRelatedService.save(studentClassRelated);

        student.setRelatedId(studentClassRelated.getId());
        boolean b = studentService.updateById(student);
        return b ? Result.succeed("创建班级成功！") : Result.failed("创建班级失败！");
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
        QueryWrapper<ClassInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("grade", classInfo.getGrade())
                .eq("class_name", classInfo.getClassName())
                .eq("class_key", classInfo.getClassKey());
        ClassInfo classInfo1 = classInfoService.getOne(queryWrapper);

        if (Objects.isNull(classInfo1)) {
            return Result.failed("班级不存在！");
        }

        // 先去查询有没有学生账号，没有的话就创建一个
        QueryWrapper<Student> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id", StpUtil.getLoginIdAsLong());
        Student student = studentService.getOne(queryWrapper1);
        if (Objects.isNull(student)) {
            student = new Student();
            student.setUserId(StpUtil.getLoginIdAsLong());
            studentService.save(student);
        }

        // 判断是否已经加入班级
        QueryWrapper<StudentClassRelated> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("student_id",student.getId())
                .eq("class_id", classInfo1.getId());
        StudentClassRelated classRelated = classRelatedService.getOne(queryWrapper2);
        if (Objects.nonNull(classRelated)) {
            return Result.failed("已经加入班级，无需再加入！");
        }

        // 创建一个学生班级关联
        StudentClassRelated studentClassRelated = new StudentClassRelated();
        studentClassRelated.setClassId(classInfo1.getId());
        studentClassRelated.setStudentId(student.getId());
        studentClassRelated.setStudentType(StudentTypeEnum.GENERAL_STUDENT.getValue());
        classRelatedService.save(studentClassRelated);

        student.setRelatedId(studentClassRelated.getId());
        studentService.updateById(student);

        return Result.succeed("加入班级成功！");
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
     * 保存切换班级
     *
     * @param id 学生班级关联 ID
     * @return Result<String>
     */
    @PostMapping("/save-change-class")
    @ResponseBody
    public Result<String> saveChangeClass(Long id) {
        // 此处 id 为关联id
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
        Student student = studentService.getOne(queryWrapper);
        student.setRelatedId(id);
        boolean b = studentService.updateById(student);
        return b ? Result.succeed("切换班级成功！") : Result.failed("切换班级失败！");
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
        map.put("classList", classInfoService.getClassInfoList(userId));
        return prefix + "/change";
    }

    /**
     * 删除班级
     *
     * @param id 班级ID
     * @return Result<String>
     */
    @GetMapping("/delete-class/{id}")
    @ResponseBody
    public Result<String> deleteClass(@PathVariable(value = "id") Long id) {
        // 此处 ID 为 班级ID
        ClassInfo classInfo = classInfoService.getById(id);
        // 判断是否是自己创建的班级
        if (classInfo.getUserId().equals(StpUtil.getLoginIdAsLong())) {
            classInfoService.removeById(id);
        }
        // 删除所有有关联的学生
        QueryWrapper<StudentClassRelated> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_id", classInfo.getId());
        boolean remove = classRelatedService.remove(queryWrapper);
        return remove ? Result.succeed("班级删除成功！") : Result.failed("班级删除失败！");
    }

    /**
     * 退出班级
     *
     * @param id 班级ID
     * @return Result<String>
     */
    @GetMapping("/delete-related/{id}")
    @ResponseBody
    public Result<String> deleteClassRelated(@PathVariable(value = "id") Long id) {
        // 先查出学生信息
        QueryWrapper<Student> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id", StpUtil.getLoginIdAsLong());
        Student student = studentService.getOne(queryWrapper1);
        System.out.println("student:" + student + ", id:" + id);
        // 删除关联关系
        QueryWrapper<StudentClassRelated> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", student.getId())
                .eq("class_id", id);
        boolean remove = classRelatedService.remove(queryWrapper);
        return remove ? Result.succeed("退出班级成功！") : Result.failed("退出班级失败！");
    }

    /**
     * 班级信息列表
     *
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
        // 当前所在班级
        if (createClassList.isEmpty()) createClassList = null;
        if (joinClassList.isEmpty()) joinClassList = null;
        Long classId = classInfoService.getClassIdByStudent(StpUtil.getLoginIdAsLong());
        if (Objects.isNull(classId)) classId = 0L;
        map.put("classId", classId);
        map.put("createClassList", createClassList);
        map.put("joinClassList", joinClassList);
        return prefix + "/list";
    }

}


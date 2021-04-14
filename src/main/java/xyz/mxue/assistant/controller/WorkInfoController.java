package xyz.mxue.assistant.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import xyz.mxue.assistant.entity.WorkInfo;
import xyz.mxue.assistant.model.PageResult;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.service.StudentService;
import xyz.mxue.assistant.service.WorkInfoService;

import javax.annotation.Resource;

/**
 * <p>
 * 作业 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Controller
@RequestMapping("/work-info")
public class WorkInfoController {

    private final String prefix = "/pages/work-info";

    @Resource
    private WorkInfoService workInfoService;

    @Resource
    private StudentService studentService;

    /**
     * 班级作业页面
     *
     * @return String
     */
    @GetMapping("/index")
    public String index(ModelMap map) {
        map.put("studentType", studentService.getStudentType(StpUtil.getLoginIdAsLong()));
        return prefix + "/index";
    }


    /**
     * 新增班级作业页面
     *
     * @return String
     */
    @GetMapping("/class-add")
    public String classAdd() {
        return prefix + "/class-add";
    }

    /**
     * 新增个人作业页面
     *
     * @return String
     */
    @GetMapping("/person-add")
    public String personAdd() {
        return prefix + "/person-add";
    }

    /**
     * 修改作业页面
     *
     * @return String
     */
    @GetMapping("/update")
    public String update() {
        return prefix + "/update";
    }

    /**
     * 删除作业
     *
     * @param id  作业ID
     * @return Result<String>
     */
    @GetMapping("/delete/{id}")
    public Result<String> delete(@PathVariable String id) {
        return null;
    }

    /**
     * 作业详情
     *
     * @param id 作业ID
     * @return String
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable String id) {
        return prefix + "/detail";
    }

    /**
     * 作业列表
     *
     * @param current  当前页
     * @param size     页面大小
     * @param workInfo 作业信息
     * @return PageResult<WorkInfo>
     */
    @GetMapping("/list")
    @ResponseBody
    public PageResult<WorkInfo> list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer current,
                                     @RequestParam(value = "limit", required = false, defaultValue = "10") Integer size,
                                     WorkInfo workInfo) {
        Page<WorkInfo> page = new Page<>(current, size);
        return PageResult.succeed(workInfoService.page(page, null));
    }

}


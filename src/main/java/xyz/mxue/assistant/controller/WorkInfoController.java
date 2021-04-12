package xyz.mxue.assistant.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.mxue.assistant.entity.WorkInfo;
import xyz.mxue.assistant.model.PageResult;
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

    /**
     * 班级作业页面
     *
     * @return String
     */
    @GetMapping("/index")
    public String index() {
        return prefix + "/index";
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


package xyz.mxue.assistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.model.vo.SysMachineVO;
import xyz.mxue.assistant.service.SysMachineService;

import javax.annotation.Resource;

/**
 * 系统属性监控控制器
 *
 * @author mxuexxmy
 * @date 4/7/2021$ 12:27 AM$
 */
@Controller
public class SysMachineController {

    @Resource
    private SysMachineService sysMachineService;

    @GetMapping("/system/info/query")
    @ResponseBody
    public Result<SysMachineVO> query() {
        return Result.succeed(sysMachineService.query());
    }
}

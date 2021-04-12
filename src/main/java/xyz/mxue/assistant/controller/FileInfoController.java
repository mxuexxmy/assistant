package xyz.mxue.assistant.controller;


import cn.hutool.core.lang.Dict;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import xyz.mxue.assistant.commons.param.FileInfoParam;
import xyz.mxue.assistant.entity.FileInfo;
import xyz.mxue.assistant.model.PageResult;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.service.FileInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 文件信息 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Controller
@RequestMapping("/file-info")
public class FileInfoController {

    @Resource
    private FileInfoService fileInfoService;

    /**
     * 上传文件
     *
     * @author yubaoshan
     * @date 2020/6/7 22:15
     */
    @ResponseBody
    @PostMapping("/upload")
    public Dict upload(@RequestPart("file") MultipartFile file) {
        Long fileId = fileInfoService.uploadFile(file);
        Result<Long> result = Result.succeed("上传成功");
        result.setData(fileId);
        Dict dict = Dict.parse(result);
        dict.put("dir", "/");
        dict.put("msg", result.getMsg());
        return dict;
    }

    /**
     * 下载文件
     *
     * @author yubaoshan, xuyuxiang
     * @date 2020/6/9 21:53
     */
    @ResponseBody
    @GetMapping("/download")
    public void download(@Validated(FileInfoParam.detail.class) FileInfoParam FileInfoParam, HttpServletResponse response) {
        fileInfoService.download(FileInfoParam, response);
    }

    /**
     * 文件预览
     *
     * @author yubaoshan, xuyuxiang
     * @date 2020/6/9 22:07
     */
    @ResponseBody
    @GetMapping("/preview")
    public void preview(@Validated(FileInfoParam.detail.class) FileInfoParam FileInfoParam, HttpServletResponse response) {
        fileInfoService.preview(FileInfoParam, response);
    }

    /**
     * 分页查询文件信息表
     *
     * @author yubaoshan
     * @date 2020/6/7 22:15
     */
    @ResponseBody
    @GetMapping("/page")
    public PageResult<FileInfo> page(@RequestParam(value = "page", required = false, defaultValue = "1") Integer current,
                                     @RequestParam(value = "limit", required = false, defaultValue = "10") Integer size,
                                     FileInfoParam FileInfoParam) {
        return fileInfoService.page(current, size, FileInfoParam);
    }

    /**
     * 获取全部文件信息表
     *
     * @author yubaoshan
     * @date 2020/6/7 22:15
     */
    @ResponseBody
    @GetMapping("/list")
    public PageResult<Dict> list(FileInfoParam FileInfoParam) {
        return fileInfoService.list(FileInfoParam);
    }

    /**
     * 查看详情文件信息表
     *
     * @author yubaoshan
     * @date 2020/6/7 22:15
     */
    @ResponseBody
    @GetMapping("/detail")
    public Result<FileInfo> detail(@Validated(FileInfoParam.detail.class) FileInfoParam FileInfoParam) {
        return Result.succeed(fileInfoService.detail(FileInfoParam));
    }

    /**
     * 删除文件信息表
     *
     * @author yubaoshan
     * @date 2020/6/7 22:15
     */
    @ResponseBody
    @PostMapping("/delete")
    public Result<String> delete(@RequestBody @Validated(FileInfoParam.delete.class) List<FileInfoParam> FileInfoParamList) {
        fileInfoService.delete(FileInfoParamList);
        return Result.succeed("删除文件成功");
    }


}


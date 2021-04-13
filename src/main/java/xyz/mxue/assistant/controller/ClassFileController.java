package xyz.mxue.assistant.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import xyz.mxue.assistant.commons.constant.ResultCode;
import xyz.mxue.assistant.commons.param.FileInfoParam;
import xyz.mxue.assistant.entity.ClassFile;
import xyz.mxue.assistant.entity.FileInfo;
import xyz.mxue.assistant.model.PageResult;
import xyz.mxue.assistant.model.Result;
import xyz.mxue.assistant.payload.UploadFileResponse;
import xyz.mxue.assistant.service.ClassFileService;
import xyz.mxue.assistant.service.ClassInfoService;
import xyz.mxue.assistant.service.FileInfoService;
import xyz.mxue.assistant.service.StudentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 班级文件 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Controller
@RequestMapping("/class-file")
public class ClassFileController {

    private final String prefix = "/pages/class-file";

    @Resource
    private FileInfoService fileInfoService;

    @Resource
    private ClassFileService classFileService;

    @Resource
    private ClassInfoService classInfoService;

    @Resource
    private StudentService studentService;

    /**
     * 班级文件列表
     *
     * @return String
     */
    @GetMapping("/index")
    public String index(ModelMap map) {
        map.put("studentType", studentService.getStudentType(StpUtil.getLoginIdAsLong()));
        return prefix + "/index";
    }

    /**
     * 班级单文件上传
     *
     * @param file 文件信息
     * @return Dict
     */
    @ResponseBody
    @PostMapping("/upload")
    public Dict upload(@RequestPart("file") MultipartFile file) {
        // 先查询是否存在班级
        Long classId = classInfoService.getClassIdByStudent(StpUtil.getLoginIdAsLong());
        if (Objects.isNull(classId)) {
            Dict dict = new Dict();
            return (Dict) dict.put("msg", "请创建班级，然后再上传文件！");
        }
        Long fileId = fileInfoService.uploadFile(file);
        // 把文件 ID 加入班级文件表
        ClassFile classFile = new ClassFile();
        classFile.setClassId(classId);
        classFile.setFileId(fileId);
        classFileService.save(classFile);
        Result<Long> result = Result.succeed(fileId);
        result.setCode("200");
        result.setMsg("上传成功");
        Dict dict = Dict.parse(result);
        dict.put("dir", "/");
        dict.put("msg", result.getMsg());
        return dict;
    }

    /**
     * 班级多文件上传
     *
     * @param files 多文件
     * @return Dict
     */
    @PostMapping("/upload-files")
    @ResponseBody
    public Dict uploadMultipleFiles(@RequestParam("file") MultipartFile[] files) {
        List<Dict> collect = Arrays.asList(files)
                .stream()
                .map(file -> upload(file))
                .collect(Collectors.toList());
        Result<Long> result = new Result<>();
        result.setCode("200");
        result.setMsg("上传成功");
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
     * 获取班级全部文件信息
     * @param FileInfoParam 文件参数
     * @return PageResult<Dict>
     */
    @ResponseBody
    @GetMapping("/list")
    public PageResult<Dict> list(FileInfoParam FileInfoParam) {
        Long classId = classInfoService.getClassIdByStudent(StpUtil.getLoginIdAsLong());
        Page<Dict> page = new Page<>();
        page.setTotal(0L);
        page.setSize(1L);
        page.setCurrent(10L);
        // 班级为空
        if (Objects.isNull(classId)) {
            return PageResult.succeed(page);
        }
        QueryWrapper<ClassFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_id", classId);
        List<ClassFile> classFileList = classFileService.list(queryWrapper);
        // file ids 为空
        if (classFileList.isEmpty()) {
            return PageResult.succeed(page);
        }
        List<Long> fileIds = new ArrayList<>();
        for (ClassFile classFile : classFileList) {
            fileIds.add(classFile.getFileId());
        }
        return fileInfoService.listByFileIds(fileIds);
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
     * 删除班级文件
     *
     * @param FileInfoParamList 文件信息列表
     * @return Result<String>
     */
    @ResponseBody
    @PostMapping("/delete")
    public Result<String> delete(@RequestBody @Validated(FileInfoParam.delete.class) List<FileInfoParam> FileInfoParamList) {
        // 删除班级文件信息
        List<Long> fileIds = new ArrayList<>();
        for (FileInfoParam fileInfoParam : FileInfoParamList) {
            fileIds.add(fileInfoParam.getId());
        }
        QueryWrapper<ClassFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("file_id", fileIds);
        classFileService.remove(queryWrapper);
        fileInfoService.delete(FileInfoParamList);
        return Result.succeed("删除文件成功");
    }


}


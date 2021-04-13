package xyz.mxue.assistant.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import xyz.mxue.assistant.commons.constant.CommonConstant;
import xyz.mxue.assistant.commons.constant.MediaTypeConstant;
import xyz.mxue.assistant.commons.constant.SymbolConstant;
import xyz.mxue.assistant.commons.context.RequestNoContext;
import xyz.mxue.assistant.commons.enumeration.FileInfoExceptionEnum;
import xyz.mxue.assistant.commons.enumeration.SysFileLocationEnum;
import xyz.mxue.assistant.commons.exception.LibreOfficeException;
import xyz.mxue.assistant.commons.exception.ServiceException;
import xyz.mxue.assistant.commons.file.FileOperator;
import xyz.mxue.assistant.commons.param.FileInfoParam;
import xyz.mxue.assistant.commons.utils.DownloadUtil;
import xyz.mxue.assistant.commons.utils.LibreOfficeUtil;
import xyz.mxue.assistant.entity.FileInfo;
import xyz.mxue.assistant.mapper.FileInfoMapper;
import xyz.mxue.assistant.model.FileInfoResult;
import xyz.mxue.assistant.model.PageResult;
import xyz.mxue.assistant.service.FileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static xyz.mxue.assistant.commons.config.FileConfig.DEFAULT_BUCKET;

/**
 * <p>
 * 文件信息 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

    private static final Log log = Log.get();

    @Resource
    private FileInfoMapper fileInfoMapper;

    @Resource
    private FileOperator fileOperator;

    @Override
    public PageResult<FileInfo> page(Integer current, Integer size, FileInfoParam FileInfoParam) {
        Page<FileInfo> page = new Page<>(current, size);
        // 构造条件
        LambdaQueryWrapper<FileInfo> queryWrapper = new LambdaQueryWrapper<>();

        // 拼接查询条件-文件存储位置（1:阿里云，2:腾讯云，3:minio，4:本地）
        if (ObjectUtil.isNotNull(FileInfoParam)) {
            if (ObjectUtil.isNotEmpty(FileInfoParam.getFileLocation())) {
                queryWrapper.like(FileInfo::getFileLocation, FileInfoParam.getFileLocation());
            }

            // 拼接查询条件-文件仓库
            if (ObjectUtil.isNotEmpty(FileInfoParam.getFileBucket())) {
                queryWrapper.like(FileInfo::getFileBucket, FileInfoParam.getFileBucket());
            }

            // 拼接查询条件-文件名称（上传时候的文件名）
            if (ObjectUtil.isNotEmpty(FileInfoParam.getFileOriginName())) {
                queryWrapper.like(FileInfo::getFileOriginName, FileInfoParam.getFileOriginName());
            }
        }

        // 查询分页结果
        return PageResult.succeed(fileInfoMapper.selectPage(page, queryWrapper));
    }

    @Override
    public PageResult<Dict> list(FileInfoParam FileInfoParam) {
        // 构造条件
        LambdaQueryWrapper<FileInfo> queryWrapper = new LambdaQueryWrapper<>();
        List<FileInfo> FileInfoList = this.list(queryWrapper);
        List<Dict> dictList = FileInfoList.stream().map(FileInfo -> {
            Dict dict = Dict.create();
            dict.put(CommonConstant.NAME, FileInfo.getFileOriginName());
            dict.put(CommonConstant.TYPE, FileInfo.getFileSuffix());
            dict.put(CommonConstant.IS_DIR, false);
            dict.put(CommonConstant.ID, FileInfo.getId());
            handleDictInfo(dict, FileInfo);
            return dict;
        }).collect(Collectors.toList());
        PageResult<Dict> pageResult = new PageResult<>();
        pageResult.setMsg("查询成功！");
        pageResult.setCode(200);
        pageResult.setData(dictList);
        return pageResult;
    }


    @Override
    public PageResult<Dict> listByFileIds(List<Long> fileIds) {
        List<FileInfo> FileInfoList = fileInfoMapper.selectBatchIds(fileIds);
        List<Dict> dictList = FileInfoList.stream().map(FileInfo -> {
            Dict dict = Dict.create();
            dict.put(CommonConstant.NAME, FileInfo.getFileOriginName());
            dict.put(CommonConstant.TYPE, FileInfo.getFileSuffix());
            dict.put(CommonConstant.IS_DIR, false);
            dict.put(CommonConstant.ID, FileInfo.getId());
            handleDictInfo(dict, FileInfo);
            return dict;
        }).collect(Collectors.toList());
        PageResult<Dict> pageResult = new PageResult<>();
        pageResult.setMsg("查询成功！");
        pageResult.setCode(200);
        pageResult.setData(dictList);
        return pageResult;
    }




    private void handleDictInfo(Dict dict, FileInfo FileInfo) {
        String suffix = FileInfo.getFileSuffix().toLowerCase();
        if (LibreOfficeUtil.isPic(suffix)) {
            dict.put("hasSm", true);
            dict.put("smUrl", "/file-info/preview?id=" + FileInfo.getId());
        }
        switch (suffix) {
            case MediaTypeConstant.FILE_HTML:
            case MediaTypeConstant.FILE_HTM:
                dict.put(CommonConstant.URL, FileInfo.getId() + SymbolConstant.PERIOD + MediaTypeConstant.FILE_HTM);
                break;
            case MediaTypeConstant.FILE_SWF:
                dict.put(CommonConstant.URL, FileInfo.getId() + SymbolConstant.PERIOD + MediaTypeConstant.FILE_FLASH);
                break;
            case MediaTypeConstant.DOC_XLS:
            case MediaTypeConstant.DOC_XLSX:
                dict.put(CommonConstant.URL, FileInfo.getId() + SymbolConstant.PERIOD + MediaTypeConstant.DOC_XLSX);
                break;
            case MediaTypeConstant.DOC_PPT:
            case MediaTypeConstant.DOC_PPTX:
                dict.put(CommonConstant.URL, FileInfo.getId() + SymbolConstant.PERIOD + MediaTypeConstant.DOC_PPT);
                break;
            case MediaTypeConstant.DOC_DOC:
            case MediaTypeConstant.DOC_DOCX:
                dict.put(CommonConstant.URL, FileInfo.getId() + SymbolConstant.PERIOD + MediaTypeConstant.DOC_DOC);
                break;
            default:
                dict.put(CommonConstant.URL, FileInfo.getId() + SymbolConstant.PERIOD + suffix);
                break;
        }
    }

    @Override
    public void add(FileInfoParam FileInfoParam) {

        // 将dto转为实体
        FileInfo FileInfo = new FileInfo();
        BeanUtil.copyProperties(FileInfoParam, FileInfo);

        this.save(FileInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<FileInfoParam> FileInfoParamList) {
        FileInfoParamList.forEach(FileInfoParam -> {
            // 查询文件的信息
            FileInfo FileInfo = this.getById(FileInfoParam.getId());

            // 删除文件记录
            this.removeById(FileInfoParam.getId());

            // 删除具体文件
            this.fileOperator.deleteFile(FileInfo.getFileBucket(), FileInfo.getFileObjectName());
        });
    }

    @Override
    public void edit(FileInfoParam FileInfoParam) {

        // 根据id查询实体
        FileInfo FileInfo = this.queryFileInfo(FileInfoParam);

        // 请求参数转化为实体
        BeanUtil.copyProperties(FileInfoParam, FileInfo);

        this.updateById(FileInfo);
    }

    @Override
    public FileInfo detail(FileInfoParam FileInfoParam) {
        return this.queryFileInfo(FileInfoParam);
    }

    @Override
    public Long uploadFile(MultipartFile file) {
        Date date = new Date();
        // 生成文件的唯一id
        Long fileId = date.getTime();

        // 获取文件原始名称
        String originalFilename = file.getOriginalFilename();

        // 获取文件后缀
        String fileSuffix = null;

        if (ObjectUtil.isNotEmpty(originalFilename)) {
            fileSuffix = StrUtil.subAfter(originalFilename, SymbolConstant.PERIOD, true);
        }
        // 生成文件的最终名称
        String finalName = fileId + SymbolConstant.PERIOD + fileSuffix;

        // 存储文件
        byte[] bytes;
        try {
            bytes = file.getBytes();
            fileOperator.storageFile(DEFAULT_BUCKET, finalName, bytes);
        } catch (IOException e) {
            throw new ServiceException(FileInfoExceptionEnum.ERROR_FILE);
        }

        // 计算文件大小kb
        long fileSizeKb = Convert.toLong(NumberUtil.div(new BigDecimal(file.getSize()), BigDecimal.valueOf(1024))
                .setScale(0, BigDecimal.ROUND_HALF_UP));

        //计算文件大小信息
        String fileSizeInfo = FileUtil.readableFileSize(file.getSize());

        // 存储文件信息
        FileInfo FileInfo = new FileInfo();
        FileInfo.setId(fileId);
        FileInfo.setFileLocation(SysFileLocationEnum.LOCAL.getCode());
        FileInfo.setFileBucket(DEFAULT_BUCKET);
        FileInfo.setFileObjectName(finalName);
        FileInfo.setFileOriginName(originalFilename);
        FileInfo.setFileSuffix(fileSuffix);
        FileInfo.setFileSizeKb(fileSizeKb);
        FileInfo.setFileSizeInfo(fileSizeInfo);
        // 增加创建用户
        FileInfo.setCreateUser(StpUtil.getLoginIdAsLong());
        this.save(FileInfo);

        // 返回文件id
        return fileId;
    }

    @Override
    public FileInfoResult getFileInfoResult(Long fileId) {
        byte[] fileBytes;
        // 获取文件名
        FileInfo FileInfo = this.getById(fileId);
        if (FileInfo == null) {
            throw new ServiceException(FileInfoExceptionEnum.NOT_EXISTED_FILE);
        }
        try {
            // 返回文件字节码
            fileBytes = fileOperator.getFileBytes(DEFAULT_BUCKET, FileInfo.getFileObjectName());
        } catch (Exception e) {
            log.error(">>> 获取文件流异常，请求号为：{}，具体信息为：{}", RequestNoContext.get(), e.getMessage());
            throw new ServiceException(FileInfoExceptionEnum.FILE_STREAM_ERROR);
        }

        FileInfoResult FileInfoResult = new FileInfoResult();
        BeanUtil.copyProperties(FileInfo, FileInfoResult);
        FileInfoResult.setFileBytes(fileBytes);

        return FileInfoResult;
    }

    @Override
    public void assertFile(Long field) {
        FileInfo FileInfo = this.getById(field);
        if (ObjectUtil.isEmpty(FileInfo)) {
            throw new ServiceException(FileInfoExceptionEnum.NOT_EXISTED);
        }
    }

    @Override
    public void preview(FileInfoParam FileInfoParam, HttpServletResponse response) {

        byte[] fileBytes;
        //根据文件id获取文件信息结果集
        FileInfoResult FileInfoResult = this.getFileInfoResult(FileInfoParam.getId());
        //获取文件后缀
        String fileSuffix = FileInfoResult.getFileSuffix().toLowerCase();
        //获取文件字节码
        fileBytes = FileInfoResult.getFileBytes();
        //如果是图片类型，则直接输出
        if (LibreOfficeUtil.isPic(fileSuffix)) {
            try {
                //设置contentType
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                //获取outputStream
                ServletOutputStream outputStream = response.getOutputStream();
                //输出
                IoUtil.write(outputStream, true, fileBytes);
            } catch (IOException e) {
                throw new ServiceException(FileInfoExceptionEnum.PREVIEW_ERROR_NOT_SUPPORT);
            }

        } else if (LibreOfficeUtil.isDoc(fileSuffix)) {
            try {
                //如果是文档类型，则使用libreoffice转换为pdf或html
                InputStream inputStream = IoUtil.toStream(fileBytes);

                //获取目标contentType（word和ppt和text转成pdf，excel转成html)
                String targetContentType = LibreOfficeUtil.getTargetContentTypeBySuffix(fileSuffix);

                //设置contentType
                response.setContentType(targetContentType);

                //获取outputStream
                ServletOutputStream outputStream = response.getOutputStream();

                //转换
                LibreOfficeUtil.convertToPdf(inputStream, outputStream, fileSuffix);

                //输出
                IoUtil.write(outputStream, true, fileBytes);
            } catch (IOException e) {
                log.error(">>> 预览文件异常", e.getMessage());
                throw new ServiceException(FileInfoExceptionEnum.PREVIEW_ERROR_NOT_SUPPORT);

            } catch (LibreOfficeException e) {
                log.error(">>> 初始化LibreOffice失败", e.getMessage());
                throw new ServiceException(FileInfoExceptionEnum.PREVIEW_ERROR_LIBREOFFICE);
            }

        } else {
            //否则不支持预览（暂时）
            throw new ServiceException(FileInfoExceptionEnum.PREVIEW_ERROR_NOT_SUPPORT);
        }
    }

    @Override
    public void download(FileInfoParam FileInfoParam, HttpServletResponse response) {
        // 获取文件信息结果集
        FileInfoResult FileInfoResult = this.getFileInfoResult(FileInfoParam.getId());
        String fileName = FileInfoResult.getFileOriginName();
        byte[] fileBytes = FileInfoResult.getFileBytes();
        DownloadUtil.download(fileName, fileBytes, response);
    }

    /**
     * 获取文件信息表
     *
     * @author yubaoshan
     * @date 2020/6/7 22:15
     */
    private FileInfo queryFileInfo(FileInfoParam FileInfoParam) {
        FileInfo FileInfo = this.getById(FileInfoParam.getId());
        if (ObjectUtil.isEmpty(FileInfo)) {
            throw new ServiceException(FileInfoExceptionEnum.NOT_EXISTED);
        }
        return FileInfo;
    }

}

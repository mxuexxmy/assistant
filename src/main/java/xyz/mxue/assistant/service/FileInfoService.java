package xyz.mxue.assistant.service;

import cn.hutool.core.lang.Dict;
import org.springframework.web.multipart.MultipartFile;
import xyz.mxue.assistant.commons.param.FileInfoParam;
import xyz.mxue.assistant.entity.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.mxue.assistant.model.FileInfoResult;
import xyz.mxue.assistant.model.PageResult;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 文件信息 服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
public interface FileInfoService extends IService<FileInfo> {


    /**
     * 分页查询文件信息表
     *
     * @param FileInfoParam 查询参数
     * @return 查询分页结果
     * @author yubaoshan
     * @date 2020/6/7 22:15
     */
    PageResult<FileInfo> page(Integer current, Integer size, FileInfoParam FileInfoParam);

    /**
     * 查询所有文件信息表
     *
     * @param FileInfoParam 查询参数
     * @return 文件信息列表
     * @author yubaoshan
     * @date 2020/6/7 22:15
     */
    PageResult<Dict> list(FileInfoParam FileInfoParam);

    /**
     * 添加文件信息表
     *
     * @param FileInfoParam 添加参数
     * @author yubaoshan
     * @date 2020/6/7 22:15
     */
    void add(FileInfoParam FileInfoParam);

    /**
     * 删除文件信息表
     *
     * @param FileInfoParamList 删除参数
     * @author yubaoshan
     * @date 2020/6/7 22:15
     */
    void delete(List<FileInfoParam> FileInfoParamList);

    /**
     * 编辑文件信息表
     *
     * @param FileInfoParam 编辑参数
     * @author yubaoshan
     * @date 2020/6/7 22:15
     */
    void edit(FileInfoParam FileInfoParam);

    /**
     * 查看详情文件信息表
     *
     * @param FileInfoParam 查看参数
     * @return 文件信息
     * @author yubaoshan
     * @date 2020/6/7 22:15
     */
    FileInfo detail(FileInfoParam FileInfoParam);

    /**
     * 上传文件，返回文件的唯一标识
     *
     * @param file 要上传的文件
     * @return 文件id
     * @author yubaoshan
     * @date 2020/6/9 21:21
     */
    Long uploadFile(MultipartFile file);

    /**
     * 获取文件信息结果集
     *
     * @param fileId 文件id
     * @return 文件信息结果集
     * @author yubaoshan
     * @date 2020/6/9 21:56
     */
    FileInfoResult getFileInfoResult(Long fileId);

    /**
     * 判断文件是否存在
     *
     * @param field 文件id
     * @author xuyuxiang
     * @date 2020/6/28 15:55
     */
    void assertFile(Long field);

    /**
     * 根据 文件集合ID 查询文件列表
     * @param fileIds id集合
     * @return PageResult<Dict>
     */
    PageResult<Dict> listByFileIds(List<Long> fileIds);

    /**
     * 文件预览
     *
     * @param FileInfoParam 文件预览参数
     * @param response         响应结果
     * @author xuyuxiang
     * @date 2020/7/7 11:23
     */
    void preview(FileInfoParam FileInfoParam, HttpServletResponse response);

    /**
     * 文件下载
     *
     * @param FileInfoParam 文件下载参数
     * @param response         响应结果
     * @author xuyuxiang
     * @date 2020/7/7 12:09
     */
    void download(FileInfoParam FileInfoParam, HttpServletResponse response);

}

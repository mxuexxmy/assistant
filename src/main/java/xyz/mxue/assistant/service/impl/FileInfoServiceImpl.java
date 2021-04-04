package xyz.mxue.assistant.service.impl;

import xyz.mxue.assistant.entity.FileInfo;
import xyz.mxue.assistant.mapper.FileInfoMapper;
import xyz.mxue.assistant.service.FileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件信息 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-04
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

}

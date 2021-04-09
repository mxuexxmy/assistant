package xyz.mxue.assistant.service.impl;

import xyz.mxue.assistant.entity.ClassInfo;
import xyz.mxue.assistant.mapper.ClassInfoMapper;
import xyz.mxue.assistant.model.vo.ChangeClassInfoVO;
import xyz.mxue.assistant.service.ClassInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 班级 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Service
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo> implements ClassInfoService {

    @Resource
    private ClassInfoMapper classInfoMapper;

    @Override
    public List<ChangeClassInfoVO> getClassInfoList(Long userId, Integer value) {
        return classInfoMapper.getClassInfoList(userId, value);
    }
}

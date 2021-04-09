package xyz.mxue.assistant.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.mxue.assistant.entity.ClassInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.mxue.assistant.model.vo.ChangeClassInfoVO;

import java.util.List;

/**
 * <p>
 * 班级 Mapper 接口
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {

    List<ChangeClassInfoVO> getClassInfoList(@Param("userId") Long userId,@Param("isNow") Integer value);
}

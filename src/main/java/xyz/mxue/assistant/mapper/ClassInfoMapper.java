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

    List<ChangeClassInfoVO> getClassInfoList(@Param("userId") Long userId);

    List<ClassInfo> getMyCreateClass(@Param("id") Long id,@Param("user_id") long loginIdAsLong);

    List<ClassInfo> getMyJoinClass(@Param("id") Long id,@Param("user_id") long loginIdAsLong);

    ClassInfo getClassInfoByRelatedId(@Param(value = "relatedId") Long relatedId);

    Long getClassIdByStudent(@Param("userId") long loginIdAsLong);
}

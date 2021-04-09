package xyz.mxue.assistant.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.mxue.assistant.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 学生 Mapper 接口
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
public interface StudentMapper extends BaseMapper<Student> {

    Integer getStudentType(@Param("userId") Long userId, @Param("classIsNow")Integer classIsNow);
}

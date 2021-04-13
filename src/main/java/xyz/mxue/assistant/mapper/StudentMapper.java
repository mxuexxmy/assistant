package xyz.mxue.assistant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.mxue.assistant.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.mxue.assistant.entity.StudentClassRelated;
import xyz.mxue.assistant.model.PageResult;
import xyz.mxue.assistant.model.vo.StudentInfoVO;

import java.util.List;

/**
 * <p>
 * 学生 Mapper 接口
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
public interface StudentMapper extends BaseMapper<Student> {

    Integer getStudentType(@Param("userId") Long userId);

    Page<StudentInfoVO> queryStudentList(Page<StudentInfoVO> page,@Param(Constants.WRAPPER) QueryWrapper<StudentInfoVO> queryWrapper);

    Long getStudentOfClassByUserId(@Param("userId") long loginIdAsLong);

}

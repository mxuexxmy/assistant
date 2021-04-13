package xyz.mxue.assistant.service;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import xyz.mxue.assistant.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.mxue.assistant.entity.StudentClassRelated;
import xyz.mxue.assistant.model.PageResult;
import xyz.mxue.assistant.model.vo.StudentInfoVO;

import java.util.List;

/**
 * <p>
 * 学生 服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
public interface StudentService extends IService<Student> {

    Integer getStudentType(Long userId);

    PageResult<StudentInfoVO> queryStudentList(Integer current, Integer size, StudentInfoVO student);

}

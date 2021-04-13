package xyz.mxue.assistant.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.mxue.assistant.entity.Student;
import xyz.mxue.assistant.entity.StudentClassRelated;
import xyz.mxue.assistant.mapper.StudentMapper;
import xyz.mxue.assistant.model.PageResult;
import xyz.mxue.assistant.model.vo.StudentInfoVO;
import xyz.mxue.assistant.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 学生 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Integer getStudentType(Long userId) {
        return studentMapper.getStudentType(userId);
    }

    @Override
    public PageResult<StudentInfoVO> queryStudentList(Integer current, Integer size, StudentInfoVO student) {
        // 先查询出班级
        Long classId = studentMapper.getStudentOfClassByUserId(StpUtil.getLoginIdAsLong());
        Page<StudentInfoVO> page = new Page<>(current, size);
        if (Objects.isNull(classId)) {
            return PageResult.succeed(page);
        }
        QueryWrapper<StudentInfoVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(classId),"c.class_id", classId)
        .like(StrUtil.isNotBlank(student.getStudentName()), "d.student_name", student.getStudentName())
        .like(StrUtil.isNotBlank(student.getStudentNo()), "d.student_no", student.getStudentNo())
        .eq(Objects.nonNull(student.getStudentType()), "c.student_type", student.getStudentType());
        Page<StudentInfoVO> pageResult = studentMapper.queryStudentList(page, queryWrapper);
        return PageResult.succeed(pageResult);
    }

}

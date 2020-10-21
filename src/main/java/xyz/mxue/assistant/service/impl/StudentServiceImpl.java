package xyz.mxue.assistant.service.impl;

import xyz.mxue.assistant.entity.Student;
import xyz.mxue.assistant.mapper.StudentMapper;
import xyz.mxue.assistant.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-10-21
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}

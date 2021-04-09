package xyz.mxue.assistant.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.mxue.assistant.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.mxue.assistant.model.vo.UserAndStudentInfoVO;

import java.io.Serializable;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-04
 */
public interface UserMapper extends BaseMapper<User> {

    UserAndStudentInfoVO getUserAndStudentType(@Param("loginId") Serializable loginId);
}

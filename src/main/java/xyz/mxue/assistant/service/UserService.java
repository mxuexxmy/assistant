package xyz.mxue.assistant.service;

import xyz.mxue.assistant.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.mxue.assistant.model.vo.UserAndStudentInfoVO;

import java.io.Serializable;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-04
 */
public interface UserService extends IService<User> {


    UserAndStudentInfoVO getUserAndStudentType(Serializable loginId);

    void updateAvatar(User user);
}


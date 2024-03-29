package xyz.mxue.assistant.service.impl;

import xyz.mxue.assistant.entity.User;
import xyz.mxue.assistant.mapper.UserMapper;
import xyz.mxue.assistant.model.vo.UserAndStudentInfoVO;
import xyz.mxue.assistant.service.FileInfoService;
import xyz.mxue.assistant.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private FileInfoService fileInfoService;

    @Override
    public UserAndStudentInfoVO getUserAndStudentType(Serializable loginId) {
        return userMapper.getUserAndStudentType(loginId);
    }

    @Override
    public void updateAvatar(User user) {
        User user1 = userMapper.selectById(user.getId());
        Long avatar = user.getAvatar();
        user1.setAvatar(avatar);
        userMapper.updateById(user1);
    }
}

package xyz.mxue.assistant.service.impl;

import xyz.mxue.assistant.entity.User;
import xyz.mxue.assistant.mapper.UserMapper;
import xyz.mxue.assistant.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
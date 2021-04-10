package xyz.mxue.assistant.service.impl;

import xyz.mxue.assistant.entity.SocialUserAuth;
import xyz.mxue.assistant.mapper.SocialUserAuthMapper;
import xyz.mxue.assistant.service.SocialUserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 社会化用户和系统用户关系表 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-11
 */
@Service
public class SocialUserAuthServiceImpl extends ServiceImpl<SocialUserAuthMapper, SocialUserAuth> implements SocialUserAuthService {

}

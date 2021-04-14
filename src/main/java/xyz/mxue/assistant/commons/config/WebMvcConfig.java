package xyz.mxue.assistant.commons.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.mxue.assistant.interceptor.LoginInterceptor;
import xyz.mxue.assistant.interceptor.PermissionInterceptor;

/**
 * @author mxuexxmy
 * @date 12/9/2020$ 11:41 PM$
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 添加自定义拦截器
     * .addPathPatterns("/**")  拦截的请求路径
     * .excludePathPatterns("/login"); 排除的请求路径
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/**");

        registry.addInterceptor(new PermissionInterceptor())
                .addPathPatterns("/**");
    }

}

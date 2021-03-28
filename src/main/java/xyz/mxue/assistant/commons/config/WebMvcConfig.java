package xyz.mxue.assistant.commons.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.mxue.assistant.interceptor.LoginInterceptor;
import xyz.mxue.assistant.interceptor.PermissionInterceptor;

/**
 * @author mxuexxmy
 * @date 12/23/2020$ 11:59 PM$
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 添加自定义拦截器
     * .addPathPatterns("/**")  拦截的请求路径
     * .excludePathPatterns("/login"); 排除的请求路径  备注：这个可以排除请求路径和资源文件路径
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","favicon.ico");

        registry.addInterceptor(new PermissionInterceptor())
                .addPathPatterns("/**");
    }

}

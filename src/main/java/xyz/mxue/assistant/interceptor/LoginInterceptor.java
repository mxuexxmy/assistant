package xyz.mxue.assistant.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.mxue.assistant.commons.ConstantUtils;
import xyz.mxue.assistant.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mxuexxmy
 * @date 12/23/2020$ 11:58 PM$
 */
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Student student = (Student) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);

        // 未登录
        if (student == null) {
            httpServletResponse.sendRedirect("/login");
            return false;
        }

        // 放行
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
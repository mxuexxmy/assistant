package xyz.mxue.assistant.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.mxue.assistant.commons.ConstantUtils;
import xyz.mxue.assistant.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mxuexxmy
 * @date 12/23/2020$ 11:59 PM$
 */
public class PermissionInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // 以 login 结尾的请求
        if (modelAndView != null && modelAndView.getViewName() != null && modelAndView.getViewName().endsWith("login")) {
            Student student = (Student) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
            if (student != null) {
                httpServletResponse.sendRedirect("/index");
            }
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
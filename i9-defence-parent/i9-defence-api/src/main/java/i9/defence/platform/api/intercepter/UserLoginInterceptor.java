package i9.defence.platform.api.intercepter;

import i9.defence.platform.model.Manager;
import i9.defence.platform.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
 * 创建时间：2017年11月15日 上午9:27:07
 * @author  lby
 * @version  
 * 
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        Session shiroSession = subject.getSession();
        Manager loginUser = (Manager) shiroSession.getAttribute("loginUser");
        String url = request.getRequestURL().toString();
        if (!url.endsWith("login") && !url.endsWith("regist") && !url.endsWith("login.html")
                && !url.endsWith("regist.html")) {
            if (loginUser == null) {
                // 跳转登录
                response.sendRedirect(Constants.S_LOGIN_PAGE);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

}

package com.taihe.databasedemo.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object admin = request.getSession().getAttribute("admin");
//        Cookie[] cookies=request.getCookies();
//
//        if(cookies != null){
//            for(Cookie cookie : cookies){
//                if(cookie.getName().equals("admin")){//todo:用cookie替代session
//                     if(cookie.getValue().equals("true"))
//                     {
//                         return true;
//                     }
//
//                }
//
//            }
//        }
//        else {
//            request.getSession().setAttribute("warnings", "您目前并非管理员，请进行身份验证！");
//            logger.warn("Unauthorized request for '" + request.getRequestURI() + "' from: " + request.getRemoteHost());
//            response.sendRedirect("/adminlogin");
//            return false;
//        }
        if (admin != null&&admin.equals(Boolean.TRUE)) {
            return true;
        } else {
            request.getSession().setAttribute("warnings", "您目前并非管理员，请进行身份验证！");
            logger.warn("Unauthorized request for '" + request.getRequestURI() + "' from: " + request.getRemoteHost());
            response.sendRedirect("/adminlogin");
            return false;
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
    }
}

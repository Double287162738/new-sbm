package com.sbm.util;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UrlInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Boolean flag = false;
        Object userId = httpServletRequest.getSession().getAttribute("user");
        if(userId==null){
            if("XMLHttpRequest".equals(httpServletRequest.getHeader("X-Requested-With"))) {
                System.out.println("拦截");
                //告诉ajax我是重定向
                httpServletResponse.setHeader("REDIRECT", "REDIRECT");
                //告诉ajax我重定向的路径
                httpServletResponse.setHeader("CONTENTPATH", "/skss/html/login.html");
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }else {
                httpServletResponse.sendRedirect("/skss/html/login.html");
            }
            flag=false;
        }else {
            flag = true;
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

}

package com.testSpringMini.demo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截器：帮助修改接口请求前/返回后的数据
//HandlerInterceptor：所有自定义拦截器或者Springboot提供的拦截器的鼻祖
//preHandle():当某个URL已经匹配到对应的额Controller中的某个方法，且在这个方法执行前执行。所以preHandle()方法可以决定是否将请求放行，这是通过返回值来决定的，返回true则放行，返回false则不回向后执行
//postHandle():当某个URL已经匹配到对应的额Controller中的某个方法，且执行完了这个方法执行。但是在DispatcherServlet视图渲染之前。所以在这个方法中有个ModelAndView参数，可以再次做一些修改动作
//afterCompletion():该方法实在整个请求处理完成后（包括视图渲染）执行，这时做一些资源的清理工作，这个方法只有在preHandle()被成功执行后并且返回true才会被执行

@Component
@Slf4j
public class DemoInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("=======preHandle======");
        log.info("=======request.getRequestURI()======"+request.getRequestURI());

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("=======postHandle======");
        log.info("=======request.getRequestURI()======"+request.getRequestURI());

        response.setCharacterEncoding("UTF-8");

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("=======afterCompletion======");
        log.info("=======request.getRequestURI()======"+request.getRequestURI());

    }



}


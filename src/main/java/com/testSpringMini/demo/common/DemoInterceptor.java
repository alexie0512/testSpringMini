
package com.testSpringMini.demo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 *
 * 拦截器：帮助修改接口请求前/返回后的数据
 * HandlerInterceptor：所有自定义拦截器或者Springboot提供的拦截器的鼻祖
 * preHandle():当某个URL已经匹配到对应的额Controller中的某个方法，且在这个方法执行前执行。所以preHandle()方法可以决定是否将请求放行，这是通过返回值来决定的，返回true则放行，返回false则不回向后执行
 * postHandle():当某个URL已经匹配到对应的额Controller中的某个方法，且执行完了这个方法执行。但是在DispatcherServlet视图渲染之前。所以在这个方法中有个ModelAndView参数，可以再次做一些修改动作
 * afterCompletion():该方法实在整个请求处理完成后（包括视图渲染）执行，这时做一些资源的清理工作，这个方法只有在preHandle()被成功执行后并且返回true才会被执行
 *
 */

@Component
@Slf4j
public class DemoInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenDb tokenDb;
    /**
     * 调用接口前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("=======preHandle======");
        log.info("=======request.getRequestURI()======"+request.getRequestURI());

        String requestUri = request.getRequestURI();

        //从请求Header获取客户端附加token
        String tokenStr = request.getHeader(UserConstants.LOGIN_TOKEN);
        boolean flag = false;

        /**
         * 放开不需要登录校验的接口
         */
        if(requestUri.contains("/hogwartsuser/login_post")
                ||requestUri.contains("/hogwartsuser/register")
                ||requestUri.contains("/jenkins/build" )){
            flag=true;
        }

        if(flag){return true;}

        //如果请求中无token, 相应码设401，抛出业务异常：客户端未传token
        if(StringUtils.isEmpty(tokenStr)){
            response.setStatus(401);
            ServiceException.throwEx("客户端未传token");
        }

        //从tokenDb中根据token查询TokenDto, 如果为空，则响应码设401，抛出业务异常：用户未登录，否则允许请求通过
        if(Objects.isNull(tokenDb.getUserInfo(tokenStr))){
            response.setStatus(401);
            ServiceException.throwEx("用户未登录");
        }
        return true;
    }


    /**
     * 调用接口后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("=======postHandle======");
        log.info("=======request.getRequestURI()======"+request.getRequestURI());

        response.setCharacterEncoding("UTF-8");

    }

    /**
     * 接口请求完成结束后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("=======afterCompletion======");
        log.info("=======request.getRequestURI()======"+request.getRequestURI());

    }



}


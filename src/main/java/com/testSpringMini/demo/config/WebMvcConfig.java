package com.testSpringMini.demo.config;


import com.testSpringMini.demo.common.DemoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;



/**
 * 配置拦截器
 * config 配置信息的转换，转换成UTF-8
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private DemoInterceptor demoInterceptor;

    /**
     *  这个方法是用来配置静态资源的，比如html,js,css等
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    /**
     * 注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        //addPathPatterns("/**") 表示拦截所有请求
        //.excludePathPatterns("/user/login","/user/register") 表示除了登录，注册之外，因为登录页和注册页不需要登录也可访问
       registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
               //.excludePathPatterns("/hogwartsuser/login","/hogwartsuser/register");
    }


    /**
     * config 配置信息的转换，转换成UTF-8
     * @param converters
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(messageConverter());
    }

    @Bean
    public HttpMessageConverter<String> messageConverter(){
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

}

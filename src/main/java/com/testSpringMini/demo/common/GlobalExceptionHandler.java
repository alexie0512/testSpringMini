/*
package com.testSpringMini.demo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({ServiceException.class})
    public String serviceExceptionHandler(){
        return "业务异常";
    }

    @ExceptionHandler({Exception.class})
    public String exceptionHandler(){
        return "非业务异常";
    }

    @ExceptionHandler({Throwable.class})
    public String throwableHandler(){
        return "系统错误🙅";
    }
}
*/

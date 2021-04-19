
package com.testSpringMini.demo.common;

import com.testSpringMini.demo.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


//ControllerAdvice:全局捕获异常，异常集中处理，更好的使业务逻辑与异常处理剥离开，定义在类上
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    //ExceptionHandler(value=Exception.class): 统一处理某一类异常，声明该方法用于捕获value所指的类型的异常（注意：当该异常的子父类都被声明时，按照线子后父的顺序进行捕获）

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ServiceException.class})
    public ResultDto serviceExceptionHandler(ServiceException se){
        log.error(se.getMessage());
        return resultFormat(se);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler({Exception.class})
    public ResultDto exceptionHandler(Exception e){
        log.error(e.getMessage());
        return resultFormat(e);
    }


    //ResponseStatus: 将某种异常映射为http状态码，可用在方法上，也可以用在类上（自定义运行时异常类）
    //resultFormat(T ex): 将异常转换为统一响应大对象
    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultDto throwableHandler(Throwable t){
        log.error(t.getMessage());
        return ResultDto.fail("系统错误🙅");
    }


    public ResultDto resultFormat(Throwable t){
        String tips="系统繁忙，请稍后重试";

        if(t instanceof ServiceException){
            return ResultDto.fail("业务异常"+tips);
        }
        if(t instanceof Exception){
            return  ResultDto.fail("非业务异常"+tips);
        }
        return  ResultDto.fail(tips);
    }



}






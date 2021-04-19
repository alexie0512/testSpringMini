package com.testSpringMini.demo.controller;


import com.testSpringMini.demo.common.ServiceException;
import com.testSpringMini.demo.dto.ResultDto;
import com.testSpringMini.demo.dto.UserDto;
import com.testSpringMini.demo.dto.addUserDto;
import com.testSpringMini.demo.service.AJUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


/**
 * @RestController : 将类标记成对外提供服务的模块
 *                  Spring会转换其返回值并自动将其写入http响应
 * @RequestMapping ： 用于类和方法，在方法级别，用于处理http响应的各种办法
 * @RequestBody : 将requestbody中的json/xml对象解析成该参数类型的javabean对象
 * Post/Put/Get/DeleteMapping: 在方法级别上使用，在方法级别上，用于处理http的各种办法
 *
 * @RequestParam :  处理get请求的参数
 * @PathVariable ： 处理动态的URI，URI的值可以作为控制器中处理方法的参数
 *
 *
 * //其他常用注解：
 * @Repository : 用于dao层的bean
 * @Autowired : 用于向一个bean中注入其他bean
 * @Service : 用于service层的bean
 * @Configuration : 用于声明springboot的配置文件类
 * @Value("${key}") : 获取springboot配置文件中的值
 * @Bean : 声明其为bean实例，常和Configuration配合使用
 *
 */


@Api(tags="Tester Field - Client Management")
@RestController
@RequestMapping("hogwartsuser")
@Slf4j
public class AJUserController {

    @Autowired
    private AJUserService ajUserService;

    @Value("${hogwarts.keys}")
    private String hogwartskeys;


    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestBody UserDto userDto){

        return "";
    }



    //get api 示例
    //requestmapping：用于类和方法，在方法级别，用于处理http响应的各种办法
    @ApiOperation("登录接口_login_get")
    @RequestMapping(value="login_get",method = RequestMethod.GET)
    public String login_get(){
        System.out.println("-----login get------");
        return "get api 示例";
    }

    //post api 示例
    //RequestBody: 将requestbody中的json/xml对象解析成该参数类型的javabean对象
    //post/put/get/deletmapping: 在方法级别上使用，在方法级别上，用于处理http的各种办法
    //@PostMapping("login")
    @ApiOperation("登录接口_login_post")
    @RequestMapping(value="login_post", method = RequestMethod.POST)
    public ResultDto login_post(@RequestBody UserDto userDto){
        System.out.println("-----login  post------");
        String result= ajUserService.login(userDto);

        if(userDto.getUsername().contains("error")){
            ServiceException.throwEx("用户名中含有error");
        }


        //return "登录成功: " + result + " hogwartskeys=" + hogwartskeys;
        return ResultDto.success("login succesfully" + result + "hogwartskeys= " + hogwartskeys,userDto);
    }


    @ApiOperation("注册接口_register")
    @RequestMapping(value="register", method = RequestMethod.POST)
    public String register(@RequestBody addUserDto addUserDto){
        System.out.println("-----register------");
        String result= ajUserService.register(addUserDto);
        return "registered successfully" +result;
    }

/*    //pathvariable：处理动态的URI，URI的值可以作为控制器中处理方法的参数
    @ApiOperation("登录接口_loginbyId/{userId}")
    @RequestMapping(value="loginbyId/{userId}",method = RequestMethod.GET)
    public Long loginbyuserId(@PathVariable("userId") Long userId){
        System.out.println("-----loginbyuserId------");
        return userId;
    }

    //@RequestMapping(value="loginbyId/{userId}/{id}",method = RequestMethod.GET)
    @ApiOperation("登录接口_loginbyId/{userId}/{id}")
    public String loginbyId(@PathVariable("userId") Long userId, @PathVariable("id") Long id){
        System.out.println("-----loginbyid------");
        return "userid:"+userId+"   id:"+id;
    }*/

    //requestparam:处理get请求的参数
    @ApiOperation("登录接口_byId?userId")
    @GetMapping("byId")
    public String byId(@RequestParam(value="userId",required=false) Long userId, @RequestParam(value="id") Long id){
        System.out.println("-----byId------");
        return "userid:"+userId+"   id:"+id;
    }


}

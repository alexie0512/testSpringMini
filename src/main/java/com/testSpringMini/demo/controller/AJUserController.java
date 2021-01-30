package com.testSpringMini.demo.controller;


import com.testSpringMini.demo.dto.UserDto;
import com.testSpringMini.demo.service.AJUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;




//restcontroller: 将类标记成对外提供服务的模块
//                Spring会转换其返回值并自动将其写入http响应
@Api(tags="Tester Field - Client Management")
@RestController
@RequestMapping("hogwarts")
public class AJUserController {

    @Autowired
    private AJUserService ajUserService;

    @Value("${hogwarts.keys}")
    private String hogwartskeys;


    //get api 示例
    //requestmapping：用于类和方法，在方法级别，用于处理http响应的各种办法
    @ApiOperation("login API")
    @RequestMapping(value="login_get",method = RequestMethod.GET)
    public String login_get(){
        System.out.println("-----login get------");
        return "get api 示例";
    }

    //post api 示例
    //RequestBody: 将requestbody中的json/xml对象解析成该参数类型的javabean对象
    //post/put/get/deletmapping: 在方法级别上使用，在方法级别上，用于处理http的各种办法
    //@PostMapping("login")
    @RequestMapping(value="login_post", method = RequestMethod.POST)
    public String login_post(@RequestBody UserDto userDto){
        System.out.println("-----login  post------");
        String result= ajUserService.login(userDto);
        return "login succesfully" +result + "hogwartskeys= " + hogwartskeys;
    }

    //pathvariable：处理动态的URI，URI的值可以作为控制器中处理方法的参数
    @RequestMapping(value="loginbyId/{userId}",method = RequestMethod.GET)
    public Long loginbyuserId(@PathVariable("userId") Long userId){
        System.out.println("-----loginbyuserId------");
        return userId;
    }

    //@RequestMapping(value="loginbyId/{userId}/{id}",method = RequestMethod.GET)
    public String loginbyId(@PathVariable("userId") Long userId, @PathVariable("id") Long id){
        System.out.println("-----loginbyid------");
        return "userid:"+userId+"   id:"+id;
    }

    //requestparams:处理get请求的参数
    @GetMapping("byId")
    public String byId(@RequestParam(value="userId",required=false) Long userId, @RequestParam(value="id") Long id){
        System.out.println("-----byId------");
        return "userid:"+userId+"   id:"+id;
    }

}

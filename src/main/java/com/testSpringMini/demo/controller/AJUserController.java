package com.testSpringMini.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.testSpringMini.demo.common.ServiceException;
import com.testSpringMini.demo.dto.ResultDto;
import com.testSpringMini.demo.dto.UpdateHogwartsTestUserDto;
import com.testSpringMini.demo.dto.UserDto;
import com.testSpringMini.demo.dto.AddHogwartsTestUserDto;
import com.testSpringMini.demo.entity.HogwartsTestUser;
import com.testSpringMini.demo.service.AJUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


    @ApiOperation("用户注册接口")
    @RequestMapping(value="register", method = RequestMethod.POST)
    public ResultDto<HogwartsTestUser> register(@RequestBody AddHogwartsTestUserDto addHogwartsTestUserDto){
        System.out.println("-----register------");

        if(StringUtils.isEmpty(addHogwartsTestUserDto.getUserName())){
            return ResultDto.fail("用户名不能为空");

        }

        if(StringUtils.isEmpty(addHogwartsTestUserDto.getPassword())){
            return ResultDto.fail("密码不能为空");
        }

        HogwartsTestUser hogwartsTestUser= new HogwartsTestUser();
        /*** 将请求对象的值赋值给数据库操作实体类*/
        //hogwartsTestUser.setUserName(addHogwartsTestUserDto.getUserName());
        //hogwartsTestUser.setPassword(addHogwartsTestUserDto.getPassword());
        //hogwartsTestUser.setEmail(addHogwartsTestUserDto.getEmail());
        BeanUtils.copyProperties(addHogwartsTestUserDto,hogwartsTestUser);

        log.info("用户注册 请求入参: "+JSONObject.toJSONString(hogwartsTestUser));
        return ajUserService.save(hogwartsTestUser);

        //return ResultDto.success("注册成功！",hogwartsTestUser);
    }


    @ApiOperation("更新用户信息接口")
    @PutMapping()
    public ResultDto updateUserInfo(@RequestBody UpdateHogwartsTestUserDto updateHogwartsTestUserDto){
        System.out.println("-----updateUserInfo------");

        if(StringUtils.isEmpty(updateHogwartsTestUserDto.getUserName())){
            return ResultDto.fail("用户名不能为空");
        }

        if(StringUtils.isEmpty(updateHogwartsTestUserDto.getPassword())){
            return ResultDto.fail("密码不能为空");
        }
        HogwartsTestUser hogwartsTestUser= new HogwartsTestUser();
        BeanUtils.copyProperties(updateHogwartsTestUserDto,hogwartsTestUser);
        log.info("用户更新 请求入参: "+JSONObject.toJSONString(hogwartsTestUser));
        return ajUserService.update(hogwartsTestUser);
    }


    @ApiOperation("根据用户名模糊查询")
    @GetMapping("searchbyNameorId")
    public ResultDto<List<HogwartsTestUser>> searchbyNameorId(@RequestParam(value="userId") Integer userId, @RequestParam(value="userName",required=false) String userName){
        log.info("-----根据用户模糊查询入参------"+userId+userName);
        HogwartsTestUser hogwartsTestUser= new HogwartsTestUser();
        hogwartsTestUser.setId(userId);
        hogwartsTestUser.setUserName(userName);
        log.info("根据用户模糊查询:"+JSONObject.toJSONString(hogwartsTestUser));
        return ajUserService.searchbyNameorId(hogwartsTestUser);
    }


    @ApiOperation("根据用户id删除用户")
    @DeleteMapping("{userId}")
    public ResultDto deletebyId(@PathVariable(value="userId") Integer userId){
        return ajUserService.deletebyId(userId);
    }



    //get api 示例
    //requestmapping：用于类和方法，在方法级别，用于处理http响应的各种办法
/*    @ApiOperation("登录接口_login_get")
    @RequestMapping(value="login_get",method = RequestMethod.GET)
    public String login_get(){
        System.out.println("-----login get------");
        return "get api 示例";
    }*/

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
/*    @ApiOperation("登录接口_byId?userId")
    @GetMapping("byId")
    public String byId(@RequestParam(value="userId",required=false) Long userId, @RequestParam(value="id") Long id){
        System.out.println("-----byId------");
        return "userid:"+userId+"   id:"+id;
    }*/


}

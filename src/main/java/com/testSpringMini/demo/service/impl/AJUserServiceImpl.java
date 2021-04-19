package com.testSpringMini.demo.service.impl;

import com.testSpringMini.demo.dto.UserDto;
import com.testSpringMini.demo.dto.addUserDto;
import com.testSpringMini.demo.service.AJUserService;
import org.springframework.stereotype.Component;


/**
 * @Component : 声明为springboot的bean
 */


@Component
public class AJUserServiceImpl implements AJUserService {

    @Override
    public String login(UserDto userDto) {
        return "用户名： "+ userDto.getUsername() + "  - 密码： " + userDto.getPwd();
    }

    @Override
    public String register(addUserDto addUserDto) {
        return addUserDto.getUsername() + "-" + addUserDto.getPwd();
    }


}


//其他常用注解：
//Repository:用于dao层的bean
//Autowired:用于向一个bean中注入其他bean
//Service:用于service层的bean
//Configuration:用于声明springboot的配置文件类
//Value("${key}"):获取springboot配置文件中的值
//Bean: 声明其为bean实例，常和Configuration配合使用

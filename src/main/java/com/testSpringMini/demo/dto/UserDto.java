package com.testSpringMini.demo.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


//lombok Data 实现getter/setter的属性封装的简化
@ApiModel(value="User Login class", description = "Request Class")
@Data
public class UserDto {

    @ApiModelProperty(value="login username",example = "alexie", required = true)
    private String username;

    @ApiModelProperty(value="login pwd",example = "123login", required = true)
    private String pwd;

}

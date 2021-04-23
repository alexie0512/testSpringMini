package com.testSpringMini.demo.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


//lombok Data 实现getter/setter的属性封装的简化
@ApiModel(value="User Info Update Class", description = "Request Class")
@Data
public class UpdateHogwartsTestUserDto {

    /*** 用户id*/
    @ApiModelProperty(value = "userid",example = "12",required = true)
    private Integer id;

    /*** 用户名*/
    @ApiModelProperty(value="userName",example = "alexie", required = true)
    private String userName;

    /*** 密码*/
    @ApiModelProperty(value="password",example = "123pwd", required = true)
    private String password;

    /*** 邮箱*/
    @ApiModelProperty(value="email",example = "XXXXX@gmail.com", required = true)
    private String email;

    /*** 自动生成用例job名称 不为空时表示已经创建job*/
    @ApiModelProperty(value="autoCreateCaseJobName", example = "")
    private String autoCreateCaseJobName;

    /*** 执行测试job名称 不为空时表示已经创建job*/
    @ApiModelProperty(value="startTestJobName", example = "")
    private String startTestJobName;

    /*** 默认Jenkins服务器*/
    @ApiModelProperty(value="defaultJenkinsId", example = "")
    private Integer defaultJenkinsId;



}

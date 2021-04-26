package com.testSpringMini.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Jenkins构建参数类
 *
 * @author Alexie on 2021/4/26
 * @ClassName BuildDto
 * @Description TODO
 * @Version 1.0
 */

@ApiModel(value="jenkins", description = "")
@Data
public class BuildDto {

    @ApiModelProperty(value="job名称",example = "Alexie_test01",required = true)
    private String jobName;

    @ApiModelProperty(value="用户Id",example = "12",required = true)
    private String userId;

    @ApiModelProperty(value = "备注信息",example = "备注",required = true)
    private String remark;

    @ApiModelProperty(value = "测试命令",example = "pwd",required = true)
    private String testCommand;
}

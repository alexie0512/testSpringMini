package com.testSpringMini.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.testSpringMini.demo.dto.BuildDto;
import com.testSpringMini.demo.dto.ResultDto;
import com.testSpringMini.demo.util.JenkinsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Jenkins 接口操作
 *
 * @author Alexie on 2021/4/26
 * @ClassName JenkinsContoller
 * @Description TODO
 * @Version 1.0
 */


@Api(tags = "Jenkins Job Management")
@RestController
@RequestMapping("jenkins")
@Slf4j
public class JenkinsContoller {


    @ApiOperation("调用Jenkins构建job")
    @PutMapping("build")
    public ResultDto build(@RequestBody BuildDto buildDto) throws IOException, URISyntaxException {

        log.info("======调用Jenkins构建job, 请求入参======="+ JSONObject.toJSONString(buildDto));
        JenkinsUtil.build(buildDto.getJobName(),buildDto.getUserId(),buildDto.getRemark(),buildDto.getTestCommand());
        return ResultDto.success("执行成功！");
    }
}

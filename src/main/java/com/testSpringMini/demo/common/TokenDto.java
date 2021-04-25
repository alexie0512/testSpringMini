package com.testSpringMini.demo.common;

import lombok.Data;

/**
 * @author Alexie on 2021/4/23
 * @ClassName TokenDto
 * @Description TODO
 * @Version 1.0
 */

@Data
public class TokenDto {
    private Integer userId;
    private String userName;
    private Integer defaultJenkinsId;
    private String token;

}

package com.testSpringMini.demo.common;

import lombok.Data;

/**
 * 基础对象
 *
 * @author Alexie on 2021/4/25
 * @ClassName DemoToken
 * @Description TODO
 * @Version 1.0
 */

@Data
public class DemoToken {
    private String token;
    private String time;
}

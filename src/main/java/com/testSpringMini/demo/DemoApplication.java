package com.testSpringMini.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @SpringBootApplication: springboot主类,用来加载springboot各种特性
 *
 */


/**
 * @MapperScan : 指定Mapper扫描路径
 */

@MapperScan("com.testSpringMini.demo.dao")
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

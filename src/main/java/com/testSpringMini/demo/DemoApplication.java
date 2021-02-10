package com.testSpringMini.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

//SpringBootApplication: springboot主类,用来加载springboot各种特性
@SpringBootApplication
@MapperScan("com.testSpringMini.demo.dao")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

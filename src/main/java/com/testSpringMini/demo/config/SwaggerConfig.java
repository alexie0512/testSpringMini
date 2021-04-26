package com.testSpringMini.demo.config;


import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        ParameterBuilder builder = new ParameterBuilder();
        builder.parameterType("header").name("token")
                .description("DemoToken value")
                .required(true)
                .modelRef(new ModelRef("string"));

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Springmini Interface")
                .apiInfo(apiInfo())
                .globalOperationParameters(Lists.newArrayList(builder.build()))
                .select().paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Springmini System")
                .description("testSpringmini接口文档")
                .contact(new Contact("alexie","","alexie.jing@gmail.com"))
                .version("1.0")
                .build();
    }


}

//swagger常用注解
//@Api(tags="Tester-Tasks Assigmment")
//@ApiOperation(value="添加测试任务")
//@
//@
//@
//@


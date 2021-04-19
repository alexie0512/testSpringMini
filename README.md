# testSpringMini
后端Demo

##### 简单运行脚本-demo
test_start_8099.sh

```
nohup java -Xms256m -Xms512m -XX:PermSize=64m -XX:MaxPermSize=128m -server -Dserver.port=8089 -jar aitest-mini.jar 'aitest-mini' --spring.profiles.active=dev >>./test_info_8099.log 2>&1 &
```

```
ps -ef | grep aitest-mini     #查询已经存在的aitest-mini的进程，进程号为10179
kill -9 10179       #停止该进程
sh test_start_8099.sh     #执行启动命令
tail -f test_info_8099.log  #实时查看服务输出日志
```
###Springboot容器常用注解：
 * @RestController : 将类标记成对外提供服务的模块，Spring会转换其返回值并自动将其写入http响应
 * @RequestMapping ： 用于类和方法，在方法级别，用于处理http响应的各种办法
 * @RequestBody : 将requestbody中的json/xml对象解析成该参数类型的javabean对象
 * Post/Put/Get/DeleteMapping: 在方法级别上使用，在方法级别上，用于处理http的各种办法
 * @RequestParam :  处理get请求的参数
 * @PathVariable ： 处理动态的URI，URI的值可以作为控制器中处理方法的参数
 ######其他常用注解：
 * @Component : 声明为springboot的bean
 * @Autowired : 用于向一个bean中注入其他bean
 * @Service : 用于service层的bean
 * @Repository : 用于dao层的bean
 * @Configuration : 用于声明springboot的配置文件类
 * @Value("${key}") : 获取springboot配置文件中的值
 * @Bean : 声明其为bean实例，常和Configuration配合使用



### 后端搭建：
- Springboot:多环境配置
- 自定义业务异常使用
- 全局异常处理
- Springboot中拦截器的使用
- 配置拦截器
- Cors跨域资源共享：
   - https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CORS
- 统一响应大对象：
    - resultCode：返回结果码 1成功 0 失败
    - message：提示信息
    - data：响应结果数据

- 分页请求对象
- 分页响应对象


### 数据持久化
- mybatis-maven依赖
- 自动生成代码-maven配置
   - mybatis配置
   - 自动生成代码- generator/config.properties
   - 自动生成代码- generator/generatorConfig.xml
- tk.mybatis使用- mapper 统一父类
   - https://mybatis.org/mybatis-3/zh/sqlmap-xml.html


#####创建用户表 hogwarts_test_user:
```
CREATE TABLE `hogwarts_test_user` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_name` VARCHAR ( 50 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
	`password` VARCHAR ( 32 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
	`email` VARCHAR ( 50 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
	`auto_create_case_job_name` VARCHAR ( 50 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '自动生成用例job名称 不为空时表示已经创建job',
	`start_test_job_name` VARCHAR ( 50 ) CHARACTER 
	SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行测试job名称 不为空时表示已经创建job',
	`default_jenkins_id` INT DEFAULT NULL COMMENT '默认Jenkins服务器',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY ( `id` ) USING BTREE 
) ENGINE = INNODB AUTO_INCREMENT = 12 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC COMMENT = '用户表';
```



# testSpringMini
backenddemo

# 简单运行脚本-demo
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

- Springboot:多环境配置
- 自定义业务异常使用
- 全局异常处理
- Springboot中拦截器的使用
- 配置拦截器
- Cors跨域资源共享
- 统一响应大对象
- 分页请求对象
- 分页响应对象

- 数据持久化
- mybatis-maven依赖
- 自动生成代码-maven配置
- mybatis配置
- 自动生成代码- config.properties
- 自动生成代码- generatorConfig.xml
- 用户表
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
- tk.mybatis使用- mapper 统一父类


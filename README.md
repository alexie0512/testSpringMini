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

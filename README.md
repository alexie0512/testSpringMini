# testSpringMini
backenddemo

#简单运行脚本

test_start_8099.sh

nohup java -Xms256m -Xms512m -XX:PermSize=64m -XX:MaxPermSize=128m -server -Dserver.port=8089 -jar aitest-mini.jar 'aitest-mini' --spring.profiles.active=dev >>./test_info_8099.log 2>&1 &

ps -ef | grep aitest-mini 
kill -9 10179
sh test_start_8099.sh
tail -f test_info_8099.log 

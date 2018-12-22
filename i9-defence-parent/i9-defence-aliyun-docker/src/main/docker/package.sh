打包生成镜像
mvn package docker:build

查看本地镜像
docker images

docker run --name web -p 8080:8080 -t -i --rm \
    --link mysql:aliasdb \
    --env iot.hosting.rds.datasource.mysqlUrl=aliasdb \
    --env iot.hosting.rds.datasource.mysqlUser=root \
    --env iot.hosting.rds.datasource.mysqlPassword=123456 \
    springboot/i9-defence-aliyun-docker

测试访问
http://127.0.0.1:8080/

docker仓库密码
tianjinhejidianqi1

docker推送远程仓库
sudo docker login --username=天津合极电气 registry.cn-shanghai.aliyuncs.com
sudo docker tag springboot/i9-defence-aliyun-docker registry.cn-shanghai.aliyuncs.com/iot-1405029890082385-w91jwh2x/zone:v1.0
sudo docker push registry.cn-shanghai.aliyuncs.com/iot-1405029890082385-w91jwh2x/zone:v1.0

远程仓库mysql密码
tianjinhejidianqi1@

环境变量
iot.hosting.rds.datasource.mysqlUser
iot.hosting.rds.datasource.mysqlPassword
iot.hosting.rds.datasource.mysqlUrl

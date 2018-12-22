mvn package docker:build

docker images

docker run --name web -p 8080:8080 -t -i --rm \
    --link mysql:aliasdb \
    --env MYSQL_HOSTNAME=aliasdb \
    --env MYSQL_PORT=3306 \
    --env MYSQL_USERNAME=root \
    --env MYSQL_PASSWORD=123456 \
    --env MYSQL_DATABASE=d \
    springboot/i9-defence-aliyun-docker


http://127.0.0.1:8080/

docker仓库密码：tianjinhejidianqi1

docker push：
sudo docker login --username=天津合极电气 registry.cn-shanghai.aliyuncs.com
sudo docker tag springboot/i9-defence-aliyun-docker registry.cn-shanghai.aliyuncs.com/iot-1405029890082385-w91jwh2x/zone:v1.0
sudo docker push registry.cn-shanghai.aliyuncs.com/iot-1405029890082385-w91jwh2x/zone:v1.0



tianjinhejidianqi1@
    
    
iot.hosting.rds.datasource.mysqlUser 用户名
iot.hosting.rds.datasource.mysqlPassword 用户密码
iot.hosting.rds.datasource.mysqlUrl



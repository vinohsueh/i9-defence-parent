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
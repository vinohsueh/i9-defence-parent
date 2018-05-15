#!/bin/bash
export M_PATH=/usr/local/environment/i9-defence-parent/i9-defence-parent

cd $M_PATH
git pull

cd $M_PATH
mvn -U clean compile package install -DskipTests -Pprod

rm -rf /tmp/ROOT.war
rm -rf /tmp/i9-defence-microservice-mq.war
rm -rf /tmp/i9-defence-microservice-observer.war
rm -rf /tmp/i9-defence-microservice-push.war
rm -rf /tmp/i9-defence-socket.war

cp -R $M_PATH/i9-defence-microservice-config/target/ROOT.war /tmp/ROOT.war
cp -R $M_PATH/i9-defence-microservice-mq/target/i9-defence-microservice-mq.war /tmp/i9-defence-microservice-mq.war
cp -R $M_PATH/i9-defence-microservice-observer/target/i9-defence-microservice-observer.war /tmp/i9-defence-microservice-observer.war
cp -R $M_PATH/i9-defence-microservice-push/target/i9-defence-microservice-push.war /tmp/i9-defence-microservice-push.war
cp -R $M_PATH/i9-defence-socket/target/i9-defence-socket.war /tmp/i9-defence-socket.war
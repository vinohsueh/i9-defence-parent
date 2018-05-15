#!/bin/bash
export M_PATH=/usr/local/environment/i9-defence-parent/i9-defence-parent

cd $M_PATH
git pull

cd $M_PATH
mvn -U clean compile package install -DskipTests

#cd $M_PATH/i9-defence-mq-libraries/
#mvn -U clean compile package install -DskipTests

#cd $M_PATH/i9-defence-netty-libraries/
#mvn -U clean compile package install -DskipTests

#cd $M_PATH/i9-defence-socket/
#mvn -U clean compile package install -DskipTests

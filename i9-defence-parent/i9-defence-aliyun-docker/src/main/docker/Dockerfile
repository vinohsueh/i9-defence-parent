FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD i9-defence-aliyun-docker.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
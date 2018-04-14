package i9.defence.platform.microservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Application extends SpringBootServletInitializer{
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

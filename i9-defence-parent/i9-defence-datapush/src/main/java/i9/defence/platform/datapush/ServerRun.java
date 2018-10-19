package i9.defence.platform.datapush;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * 服务启动类
 * 
 * @author R12
 * @date 2018年10月8日 08:52:54
 */
@EnableTransactionManagement
@EnableScheduling
@Configuration
@SpringBootApplication
public class ServerRun {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServerRun.class, args);
    }
}

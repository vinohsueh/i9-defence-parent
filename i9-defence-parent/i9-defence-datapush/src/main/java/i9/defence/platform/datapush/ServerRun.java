package i9.defence.platform.datapush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 服务启动类
 * 
 * @author R12
 * @date 2018年10月8日 08:52:54
 */
@EnableTransactionManagement
@SpringBootApplication
public class ServerRun {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServerRun.class, args);
    }
}

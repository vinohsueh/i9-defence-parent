package i9.defence.platform.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:activemq.xml")
public class ActiveMqConfig {
}

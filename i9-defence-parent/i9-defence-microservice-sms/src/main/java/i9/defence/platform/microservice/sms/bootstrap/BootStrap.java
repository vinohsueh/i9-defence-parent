package i9.defence.platform.microservice.sms.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(BootStrap.class);

    /**
     * 服务器启动执行线程
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("消息队列服务器已启动");
    }
    
}

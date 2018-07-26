package i9.defence.platform.microservice.push.bootstrap;

import i9.defence.platform.microservice.push.pool.ActiveMQConsumerRunnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(BootStrap.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Thread thread = new Thread(activeMQConsumerRunnable);
        thread.start();
        logger.info("消息队列服务器已启动");
    }
    
    @Autowired
    private ActiveMQConsumerRunnable activeMQConsumerRunnable;
}

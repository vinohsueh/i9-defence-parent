package i9.defence.platform.microservice.mq.bootstrap;

import i9.defence.platform.microservice.mq.pool.runnable.ActiveMQBusinessConsumerRunnable;
import i9.defence.platform.microservice.mq.pool.runnable.ActiveMQConnectConsumerRunnable;
import i9.defence.platform.microservice.mq.pool.runnable.ActiveMQDisConnectConsumerRunnable;

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
        new Thread(activeMQBusinessConsumerRunnable).start();
        new Thread(activeMQConnectConsumerRunnable).start();
        new Thread(activeMQDisConnectConsumerRunnable).start();
        logger.info("消息队列服务器已启动");
    }
    
    @Autowired
    private ActiveMQBusinessConsumerRunnable activeMQBusinessConsumerRunnable;
    
    @Autowired
    private ActiveMQConnectConsumerRunnable activeMQConnectConsumerRunnable;
    
    @Autowired
    private ActiveMQDisConnectConsumerRunnable activeMQDisConnectConsumerRunnable;
}

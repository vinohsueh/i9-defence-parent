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

    /**
     * 服务器启动执行线程
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 启动业务消息队列线程
        new Thread(activeMQBusinessConsumerRunnable, "BusinessThread").start();
        
        // 启动设备连接通知线程
        new Thread(activeMQConnectConsumerRunnable, "ConnectThread").start();
        
        // 启动设备掉线通知线程
        new Thread(activeMQDisConnectConsumerRunnable, "DisConnectThread").start();
        
        logger.info("消息队列服务器已启动");
    }
    
    @Autowired
    private ActiveMQBusinessConsumerRunnable activeMQBusinessConsumerRunnable;
    
    @Autowired
    private ActiveMQConnectConsumerRunnable activeMQConnectConsumerRunnable;
    
    @Autowired
    private ActiveMQDisConnectConsumerRunnable activeMQDisConnectConsumerRunnable;
}

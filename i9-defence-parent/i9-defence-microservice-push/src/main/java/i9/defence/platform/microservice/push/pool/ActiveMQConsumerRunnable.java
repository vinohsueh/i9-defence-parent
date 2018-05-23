package i9.defence.platform.microservice.push.pool;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import i9.defence.platform.mq.libraries.consumer.ActiveMQConsumerService;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;

@Component
public class ActiveMQConsumerRunnable implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                TextMessage textMessage = activeMQConsumerService.receive(ActiveMQQueueEnum.I9_PUSH);
                // 如果数据为空就延迟3秒钟
                if (textMessage == null) {
                    continue;
                }
                activeMQConsumerRunnablePool.executePool(textMessage);
            } catch (Exception e) {
                logger.error("active mq receive push queue error, ", e);
            }
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQConsumerRunnable.class);
    
    @Autowired
    private ActiveMQConsumerService activeMQConsumerService;
    
    @Autowired
    private ActiveMQConsumerRunnablePool activeMQConsumerRunnablePool;
}

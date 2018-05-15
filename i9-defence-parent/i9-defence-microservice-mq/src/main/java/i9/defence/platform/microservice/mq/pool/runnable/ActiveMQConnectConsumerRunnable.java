package i9.defence.platform.microservice.mq.pool.runnable;

import i9.defence.platform.mq.libraries.consumer.ActiveMQConsumerService;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;

import javax.annotation.Resource;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQConnectConsumerRunnable implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                final TextMessage textMessage = activeMQConsumerService.receive(ActiveMQQueueEnum.I9_CONNECT);
                // 如果数据为空就延迟3秒钟
                if (textMessage == null) {
                    Thread.sleep(3000);
                    continue;
                }
                logger.info("I9_CONNECT {}, SUCCESS", textMessage.getText());
            } catch (Exception e) {
                logger.info("I9_CONNECT RECEIVE, ERROR", e);
            }
        }
    }
    
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQConnectConsumerRunnable.class);

    @Resource
    private ActiveMQConsumerService activeMQConsumerService;
}

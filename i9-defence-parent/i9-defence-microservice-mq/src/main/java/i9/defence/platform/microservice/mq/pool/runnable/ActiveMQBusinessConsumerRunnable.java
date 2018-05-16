package i9.defence.platform.microservice.mq.pool.runnable;

import i9.defence.platform.microservice.mq.pool.ActiveMQBusinessPool;
import i9.defence.platform.microservice.mq.service.impl.ActiveMQBusinessConsumerTask;
import i9.defence.platform.mq.libraries.consumer.ActiveMQConsumerService;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;

import javax.annotation.Resource;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQBusinessConsumerRunnable implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                // 读取消息队列中I9_BUSINESS消息
                final TextMessage textMessage = activeMQConsumerService.receive(ActiveMQQueueEnum.I9_BUSINESS);
                // 如果数据为空就延迟3秒钟
                if (textMessage == null) {
//                    Thread.sleep(3000);
                    continue;
                }
                // 处理消息
                activeMQBusinessPool.execute(new ActiveMQBusinessConsumerTask(textMessage));
                logger.info("I9_BUSINESS {}, SUCCESS", textMessage.getText());
            } catch (Exception e) {
                logger.info("I9_BUSINESS RECEIVE, ERROR", e);
            }
        }
    }
    
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQBusinessConsumerRunnable.class);

    @Resource
    private ActiveMQConsumerService activeMQConsumerService;

    @Autowired
    private ActiveMQBusinessPool activeMQBusinessPool;
}

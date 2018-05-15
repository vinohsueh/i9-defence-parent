package i9.defence.platform.microservice.mq.pool.runnable;

import i9.defence.platform.microservice.mq.pool.ActiveMQBusinessPool;
import i9.defence.platform.microservice.mq.service.ActiveMQBusinessConsumerTask;
import i9.defence.platform.mq.libraries.consumer.ActiveMQConsumerService;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.service.UpStreamDecodeService;

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
                final TextMessage textMessage = activeMQConsumerService.receive(ActiveMQQueueEnum.I9_BUSINESS);
                // 如果数据为空就延迟3秒钟
                if (textMessage == null) {
                    Thread.sleep(3000);
                    continue;
                }
                activeMQBusinessPool.execute(new ActiveMQBusinessConsumerTask(upStreamDecodeService, textMessage));
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
    private UpStreamDecodeService upStreamDecodeService;
    
    @Autowired
    private ActiveMQBusinessPool activeMQBusinessPool;
}

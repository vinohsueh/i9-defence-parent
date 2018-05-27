package i9.defence.platform.microservice.mq.listener;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import i9.defence.platform.microservice.mq.pool.ActiveMQBusinessPool;
import i9.defence.platform.microservice.mq.service.impl.ActiveMQBusinessConsumerTask;
import i9.defence.platform.mq.libraries.consumer.ActiveMQConsumerService;

@Component
public class ActiveMQBusinessConsumerMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {  
        try {
        	TextMessage textMessage = (TextMessage) message; 
            // 处理消息
            activeMQBusinessPool.execute(new ActiveMQBusinessConsumerTask(textMessage));
            logger.info("I9_BUSINESS {}, SUCCESS", textMessage.getText());
        } catch (Exception e) {
            logger.info("I9_BUSINESS RECEIVE, ERROR", e);
        }
    }
    
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQBusinessConsumerMessageListener.class);

    @Resource
    private ActiveMQConsumerService activeMQConsumerService;

    @Autowired
    private ActiveMQBusinessPool activeMQBusinessPool;
}

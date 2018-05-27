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
import i9.defence.platform.microservice.mq.service.impl.ActiveMQConnectionConsumerTask;
import i9.defence.platform.mq.libraries.consumer.ActiveMQConsumerService;

@Component
public class ActiveMQDeviceStateConsumerMessageListener implements MessageListener {  

    @Override
    public void onMessage(Message message) {  
        try {
        	TextMessage textMessage = (TextMessage) message; 
            // 处理消息
            activeMQBusinessPool.execute(new ActiveMQConnectionConsumerTask(textMessage));
            logger.info("I9_DEVICE_STATE {}, SUCCESS", textMessage.getText());
        } catch (Exception e) {
            logger.info("I9_DEVICE_STATE RECEIVE, ERROR", e);
        }
    }
    
    @Autowired
    private ActiveMQBusinessPool activeMQBusinessPool;
    
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQDeviceStateConsumerMessageListener.class);

    @Resource
    private ActiveMQConsumerService activeMQConsumerService;
}

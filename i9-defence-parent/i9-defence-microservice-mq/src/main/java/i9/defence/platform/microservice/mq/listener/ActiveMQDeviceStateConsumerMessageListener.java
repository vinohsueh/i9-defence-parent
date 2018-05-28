package i9.defence.platform.microservice.mq.listener;

import i9.defence.platform.microservice.mq.pool.BusinessPool;
import i9.defence.platform.microservice.mq.service.impl.ActiveMQDeviceStatusConsumerTask;
import i9.defence.platform.mq.libraries.consumer.ActiveMQConsumerService;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQDeviceStateConsumerMessageListener implements MessageListener {  

    @Override
    public void onMessage(Message message) {  
        try {
        	TextMessage textMessage = (TextMessage) message; 
            // 处理消息
        	pool.execute(new ActiveMQDeviceStatusConsumerTask(textMessage));
            logger.info("I9_DEVICE_STATE {}, SUCCESS", textMessage.getText());
        } catch (Exception e) {
            logger.info("I9_DEVICE_STATE RECEIVE, ERROR", e);
        }
    }
    
    @Autowired
    private BusinessPool pool;
    
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQDeviceStateConsumerMessageListener.class);

    @Resource
    private ActiveMQConsumerService activeMQConsumerService;
}

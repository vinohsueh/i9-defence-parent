package i9.defence.platform.microservice.sms.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import i9.defence.platform.microservice.sms.pool.BusinessPool;
import i9.defence.platform.microservice.sms.service.ActiveMQAlarmSMSConsumerTask;

@Component
public class ActiveMQSMSConsumerMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {  
        try {
        	TextMessage textMessage = (TextMessage) message; 
            // 处理消息
        	pool.execute(new ActiveMQAlarmSMSConsumerTask(textMessage));
            logger.info("I9_SMS {}, SUCCESS", textMessage.getText());
        } catch (Exception e) {
            logger.info("I9_SMS RECEIVE, ERROR", e);
        }
    }
    
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQSMSConsumerMessageListener.class);

    @Autowired
    private BusinessPool pool;
}

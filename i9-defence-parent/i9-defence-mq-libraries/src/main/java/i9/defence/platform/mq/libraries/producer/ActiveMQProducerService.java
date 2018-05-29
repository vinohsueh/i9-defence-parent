package i9.defence.platform.mq.libraries.producer;

import i9.defence.platform.mq.libraries.destination.ActiveMQDestination;
import i9.defence.platform.mq.libraries.destination.ActiveMQDestinationHolder;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQProducerService {
    
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQProducerService.class);

    @Autowired
    private JmsTemplate jmsTemplate;
    
    @Autowired
    private ActiveMQDestinationHolder activeMQDestinationHolder;
    
    public void sendMessage(ActiveMQQueueEnum activeMQQueueEnum, final String msg) {
        ActiveMQDestination destination = activeMQDestinationHolder.getDestination(activeMQQueueEnum);
        this.sendMessage(destination, msg);
    }

    public void sendMessage(Destination destination, final String msg) {
        logger.info("发送MQ数据, text : {}, destination :{}", msg, destination);
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        };
        jmsTemplate.send(destination, messageCreator);
    }
}

package i9.defence.platform.mq.libraries.consumer;

import i9.defence.platform.mq.libraries.destination.ActiveMQDestination;
import i9.defence.platform.mq.libraries.destination.ActiveMQDestinationHolder;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQConsumerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public TextMessage receive(Destination destination) {
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
        if (textMessage != null) {
            try {
                logger.info("接受MQ数据, text : {}, destination : {}", textMessage.getText(), destination);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return textMessage;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQConsumerService.class);
    
    public TextMessage receive(ActiveMQQueueEnum activeMQQueueEnum) {
        ActiveMQDestination destination = this.activeMQDestinationHolder.getDestination(activeMQQueueEnum);
        return this.receive(destination);
    }
    
    @Autowired
    private ActiveMQDestinationHolder activeMQDestinationHolder;
}

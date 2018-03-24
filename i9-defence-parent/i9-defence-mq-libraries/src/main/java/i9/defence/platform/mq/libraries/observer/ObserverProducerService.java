package i9.defence.platform.mq.libraries.observer;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class ObserverProducerService {
    
    private static final Logger logger = LoggerFactory.getLogger(ObserverProducerService.class);

    @Resource(name = "observerJmsTemplate")
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, final String msg) {
        logger.info("发送MQ数据, text : {}, destination :{}", msg, destination);
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        };
        jmsTemplate.send(destination, messageCreator);
    }
    
    @Resource(name = "observerDestination")
    private Destination destination;

    public void sendMessage(final String msg) {
        this.sendMessage(destination, msg);
    }
}

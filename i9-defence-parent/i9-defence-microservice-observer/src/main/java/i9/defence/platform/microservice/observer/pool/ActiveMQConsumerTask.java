package i9.defence.platform.microservice.observer.pool;

import i9.defence.platform.microservice.observer.util.SpringBeanService;
import i9.defence.platform.service.UpStreamOriginService;

import java.util.TimerTask;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveMQConsumerTask extends TimerTask {

    @Override
    public void run() {
        UpStreamOriginService upStreamOriginService = SpringBeanService.getBean(UpStreamOriginService.class);
        try {
            upStreamOriginService.saveUpStreamOrigin(textMessage.getText());
            logger.info("save up stream decode success, data : " + textMessage.getText());
        } catch (Exception e) {
            logger.error("save up stream decode error, ex : ", e);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQConsumerTask.class);

    private final TextMessage textMessage;

    public ActiveMQConsumerTask(TextMessage textMessage) {
        this.textMessage = textMessage;
    }
}

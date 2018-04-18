package i9.defence.platform.microservice.observer.service;

import i9.defence.platform.microservice.observer.pool.ConsumerRunnable;
import i9.defence.platform.service.UpStreamOriginService;

import java.util.TimerTask;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerServiceRunnable extends TimerTask {

    @Override
    public void run() {
        try {
            this.upStreamOriginService.saveUpStreamOrigin(textMessage.getText());
            logger.info("save up stream decode success, data : " + textMessage.getText());
        } catch (Exception e) {
            logger.error("save up stream decode error, ex : ", e);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);

    private final TextMessage textMessage;

    private UpStreamOriginService upStreamOriginService;

    public ConsumerServiceRunnable(UpStreamOriginService upStreamOriginService, TextMessage textMessage) {
        this.upStreamOriginService = upStreamOriginService;
        this.textMessage = textMessage;
    }
}

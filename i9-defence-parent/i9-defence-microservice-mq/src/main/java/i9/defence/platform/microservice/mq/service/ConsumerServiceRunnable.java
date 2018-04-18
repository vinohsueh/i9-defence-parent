package i9.defence.platform.microservice.mq.service;

import i9.defence.platform.service.UpStreamDecodeService;

import java.util.TimerTask;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerServiceRunnable extends TimerTask {

    @Override
    public void run() {
        try {
            upStreamDecodeService.saveUpStreamDecode(textMessage.getText());
            logger.info("save up stream decode success, data : " + textMessage.getText());
        } catch (Exception e) {
            logger.error("save up stream decode error, ex : ", e);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(ConsumerServiceRunnable.class);

    private final UpStreamDecodeService upStreamDecodeService;

    private final TextMessage textMessage;

    public ConsumerServiceRunnable(UpStreamDecodeService upStreamDecodeService, TextMessage textMessage) {
        this.upStreamDecodeService = upStreamDecodeService;
        this.textMessage = textMessage;
    }
}

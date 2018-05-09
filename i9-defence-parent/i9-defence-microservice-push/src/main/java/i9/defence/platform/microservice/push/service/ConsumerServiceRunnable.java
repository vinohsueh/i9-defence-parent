package i9.defence.platform.microservice.push.service;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsumerServiceRunnable {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerServiceRunnable.class);

    private final ExecutorService pool = Executors.newCachedThreadPool();

    private ThirdPlatformService thirdPlatformService;

    public void executePool(final TextMessage textMessage) {
        pool.execute(new TimerTask() {

            @Override
            public void run() {
                try {
                    thirdPlatformService.saveAlertOrigin(textMessage.getText());
                    logger.info("push message : " + textMessage.toString());
                } catch (Exception e) {
                    logger.error("save up stream decode error, ex : ", e);
                }
            }
        });
    }

}

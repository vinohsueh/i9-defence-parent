package i9.defence.platform.microservice.observer.pool;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import i9.defence.platform.service.UpStreamOriginService;

@Component
public class ConsumerRunnable implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                TextMessage textMessage = consumerService.receive();
                // 如果数据为空就延迟3秒钟
                if (textMessage == null) {
                    Thread.sleep(3000);
                    continue;
                }
                this.upStreamOriginService.saveUpStreamOrigin(textMessage.getText());
                logger.info("save up stream origin data success, data : " + textMessage.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private static final Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private UpStreamOriginService upStreamOriginService;
}

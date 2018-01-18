package i9.defence.platform.mq.pool;

import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import i9.defence.platform.mq.libraries.ConsumerService;

@Component
public class ConsumerRunnable implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                TextMessage textMessage = consumerService.receive();
                if (textMessage == null) {
                    Thread.sleep(3000);
                    continue;
                }
            }
            catch (Exception e) {
            }
        }
    }
    
    @Autowired
    private ConsumerService consumerService;

}

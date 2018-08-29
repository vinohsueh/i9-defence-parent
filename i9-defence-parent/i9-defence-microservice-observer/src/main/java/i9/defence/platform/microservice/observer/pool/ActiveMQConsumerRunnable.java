package i9.defence.platform.microservice.observer.pool;

import i9.defence.platform.mq.libraries.consumer.ActiveMQConsumerService;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQConsumerRunnable implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                TextMessage textMessage = activeMQConsumerService.receive(ActiveMQQueueEnum.I9_OBSERVER);
                // 如果数据为空就延迟3秒钟
                if (textMessage == null) {
                    continue;
                }
                executorService.execute(new ActiveMQConsumerTask(textMessage));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private final ExecutorService executorService = Executors.newFixedThreadPool(50);
    
    @Autowired
    private ActiveMQConsumerService activeMQConsumerService;
}

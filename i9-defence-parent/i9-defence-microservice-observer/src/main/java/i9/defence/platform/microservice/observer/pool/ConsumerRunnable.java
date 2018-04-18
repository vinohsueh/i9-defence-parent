package i9.defence.platform.microservice.observer.pool;

import i9.defence.platform.microservice.observer.service.ConsumerServiceRunnable;
import i9.defence.platform.service.UpStreamOriginService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                executorService.execute(new ConsumerServiceRunnable(upStreamOriginService, textMessage));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Autowired
    private UpStreamOriginService upStreamOriginService;
    
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    @Autowired
    private ConsumerService consumerService;
}

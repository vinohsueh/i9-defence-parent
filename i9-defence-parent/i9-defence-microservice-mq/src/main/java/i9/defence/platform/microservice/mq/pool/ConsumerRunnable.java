package i9.defence.platform.microservice.mq.pool;

import i9.defence.platform.microservice.mq.service.ConsumerServiceRunnable;
import i9.defence.platform.service.UpStreamDecodeService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRunnable implements Runnable {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void run() {
        while (true) {
            try {
                final TextMessage textMessage = consumerService.receive();
                // 如果数据为空就延迟3秒钟
                if (textMessage == null) {
                    Thread.sleep(3000);
                    continue;
                }
                
                executorService.execute(new ConsumerServiceRunnable(upStreamDecodeService, textMessage));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Resource
    private ConsumerService consumerService;

    @Autowired
    private final UpStreamDecodeService upStreamDecodeService = null;
}

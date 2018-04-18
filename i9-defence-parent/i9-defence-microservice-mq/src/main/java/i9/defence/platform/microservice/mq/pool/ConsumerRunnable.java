package i9.defence.platform.microservice.mq.pool;

import i9.defence.platform.service.UpStreamDecodeService;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
                executorService.execute(new TimerTask() {

                    @Override
                    public void run() {
                        try {
                            upStreamDecodeService.saveUpStreamDecode(textMessage.getText());
                            logger.info("save up stream decode success, data : " + textMessage.getText());
                        } catch (Exception e) {
                            logger.error("save up stream decode error, ex : ", e);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);

    @Resource
    private ConsumerService consumerService;

    @Autowired
    private final UpStreamDecodeService upStreamDecodeService = null;
}

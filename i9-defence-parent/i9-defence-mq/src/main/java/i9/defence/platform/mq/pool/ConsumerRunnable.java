package i9.defence.platform.mq.pool;

import javax.annotation.Resource;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import i9.defence.platform.service.UpStreamDecodeService;

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
                this.upStreamDecodeService.saveUpStreamDecode(textMessage.getText());
                logger.info("save up stream decode success, data : " + textMessage.getText());
                
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private static final Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);
    
    @Resource
    private ConsumerService consumerService;

    @Autowired
    private UpStreamDecodeService upStreamDecodeService;
}

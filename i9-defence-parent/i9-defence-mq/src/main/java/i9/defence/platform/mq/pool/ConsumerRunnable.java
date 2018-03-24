package i9.defence.platform.mq.pool;

import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import i9.defence.platform.mq.libraries.business.BusinessConsumerService;
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
                // TODO 在这里处理数据库逻辑操作
                this.upStreamDecodeService.saveUpStreamDecode(textMessage.getText());
                System.out.println("dsfsf");
                
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Autowired
    private BusinessConsumerService consumerService;

    @Autowired
    private UpStreamDecodeService upStreamDecodeService;
}

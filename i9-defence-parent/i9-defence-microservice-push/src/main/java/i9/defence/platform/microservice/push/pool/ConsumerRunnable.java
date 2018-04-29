package i9.defence.platform.microservice.push.pool;

import java.util.List;
import java.util.Map;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import i9.defence.platform.microservice.push.repository.TestDao;
import i9.defence.platform.microservice.push.service.ConsumerServiceRunnable;

@Component
public class ConsumerRunnable implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                List<Map<String, Object>> list = testDao.getTestList();
                for (Map<String, Object> map : list) {
                    System.out.println(map);
                }
                TextMessage textMessage = consumerService.receive();
                // 如果数据为空就延迟3秒钟
                if (textMessage == null) {
                    Thread.sleep(3000);
                    continue;
                }
                consumerServiceRunnable.executePool(textMessage);
            } catch (Exception e) {
                logger.error("active mq receive push queue error, ", e);
            }
        }
    }
    
    @Autowired
    private TestDao testDao;
    
    private static final Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);
    
    @Autowired
    private ConsumerService consumerService;
    
    @Autowired
    private ConsumerServiceRunnable consumerServiceRunnable;
}

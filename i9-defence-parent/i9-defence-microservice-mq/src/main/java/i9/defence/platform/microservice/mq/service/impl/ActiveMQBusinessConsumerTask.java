package i9.defence.platform.microservice.mq.service.impl;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import i9.defence.platform.microservice.mq.service.ActiveMQConsumerTask;
import i9.defence.platform.microservice.mq.util.SpringBeanService;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.mq.libraries.producer.ActiveMQProducerService;
import i9.defence.platform.service.UpStreamDecodeService;

/**
 * 处理ActiveMQ业务消息处理任务
 * @author r12
 *
 */
public class ActiveMQBusinessConsumerTask extends ActiveMQConsumerTask {

    @Override
    public void run() {
        UpStreamDecodeService upStreamDecodeService = getUpStreamDecodeService();
        try {
            upStreamDecodeService.saveUpStreamDecode(textMessage.getText());
            logger.info("save up stream decode success, data : " + textMessage.getText());
            // 处理推送到第三方平台数据接口
             ActiveMQProducerService activeMQProducerService = SpringBeanService.getBean(ActiveMQProducerService.class);
             activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_PUSH, textMessage.getText());
        } catch (Exception e) {
            logger.error("save up stream decode error, ex : ", e);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQBusinessConsumerTask.class);

    private final TextMessage textMessage;

    public ActiveMQBusinessConsumerTask(TextMessage textMessage) {
        this.textMessage = textMessage;
    }
}

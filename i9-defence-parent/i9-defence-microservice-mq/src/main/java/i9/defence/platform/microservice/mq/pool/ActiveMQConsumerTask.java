package i9.defence.platform.microservice.mq.pool;

import i9.defence.platform.microservice.mq.util.SpringBeanService;
import i9.defence.platform.model.ConnectLog;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.mq.libraries.producer.ActiveMQProducerService;
import i9.defence.platform.service.UpStreamDecodeService;

import java.util.Date;
import java.util.TimerTask;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveMQConsumerTask extends TimerTask {

    @Override
    public void run() {
        try {
            upStreamDecodeService.saveUpStreamDecode(textMessage.getText());
            logger.info("save up stream decode success, data : " + textMessage.getText());
            
            //通过设备唯一标识更新状态
            String deviceId = "";
            int status = 0;  //0 掉线   1  离线 
            Date date = new Date();
            upStreamDecodeService.updateEquipmentStatus(deviceId,status);
            
            //插入离线掉线 记录 
            ConnectLog connectLog = new ConnectLog();
            connectLog.setDeviceId(deviceId);//设备唯一标识
            connectLog.setStatus(status);
            connectLog.setCreateTime(date);//时间
            upStreamDecodeService.insertConnectRecord(connectLog);
            
            // TODO 在这里处理推送到第三方平台数据接口
            ActiveMQProducerService activeMQProducerService = SpringBeanService.getBean(ActiveMQProducerService.class);
            activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_PUSH, "this is Test");
        } catch (Exception e) {
            logger.error("save up stream decode error, ex : ", e);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQConsumerTask.class);

    private final UpStreamDecodeService upStreamDecodeService;
    
    private final TextMessage textMessage;

    public ActiveMQConsumerTask(UpStreamDecodeService upStreamDecodeService, TextMessage textMessage) {
        this.upStreamDecodeService = upStreamDecodeService;
        this.textMessage = textMessage;
    }
}

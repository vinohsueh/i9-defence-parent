package i9.defence.platform.microservice.mq.service;

import i9.defence.platform.microservice.mq.util.SpringBeanService;
import i9.defence.platform.service.UpStreamDecodeService;

import java.util.TimerTask;

public abstract class ActiveMQConsumerTask extends TimerTask {

    protected UpStreamDecodeService getUpStreamDecodeService() {
        UpStreamDecodeService upStreamDecodeService = SpringBeanService.getBean(UpStreamDecodeService.class);
        return upStreamDecodeService;
    }
}

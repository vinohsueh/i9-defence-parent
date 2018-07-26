package i9.defence.platform.microservice.sms.service;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveMQAlarmSMSConsumerTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQAlarmSMSConsumerTask.class);

    private final TextMessage textMessage;

    public ActiveMQAlarmSMSConsumerTask(TextMessage textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public void run() {
    }
}

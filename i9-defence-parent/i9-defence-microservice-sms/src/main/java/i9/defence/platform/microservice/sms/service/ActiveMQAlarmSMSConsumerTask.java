package i9.defence.platform.microservice.sms.service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.utils.AliyunUtil;

public class ActiveMQAlarmSMSConsumerTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQAlarmSMSConsumerTask.class);

    private final TextMessage textMessage;

    public ActiveMQAlarmSMSConsumerTask(TextMessage textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public void run() {
        String jsonStr = "";
        try {
            jsonStr = textMessage.getText();
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            String templateNum = jsonObject.getString("templateNum");
            String phones = jsonObject.getString("phones");
            String clientNames = jsonObject.getString("clientNames");
            String signNames = jsonObject.getString("signNames");
            AliyunUtil.sendInfo(templateNum, phones, clientNames, signNames);
            logger.info("发送短信mq, 接收数据 : " + jsonStr + ", 成功");
        } catch (JMSException e) {
            logger.info("发送短信mq, 接收数据 : " + jsonStr + ", 失败", e);
        }
    }
}

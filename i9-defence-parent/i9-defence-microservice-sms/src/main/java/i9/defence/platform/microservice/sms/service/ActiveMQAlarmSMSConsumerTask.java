package i9.defence.platform.microservice.sms.service;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.dao.MessageLogDao;
import i9.defence.platform.enums.AliyunCodeTypeEnum;
import i9.defence.platform.model.MessageLog;
import i9.defence.platform.utils.AliyunUtil;
import i9.defence.platform.utils.StringUtil;

public class ActiveMQAlarmSMSConsumerTask implements Runnable {

    @Autowired
    private MessageLogDao messageLogDao;

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQAlarmSMSConsumerTask.class);

    private final TextMessage textMessage;

    public ActiveMQAlarmSMSConsumerTask(TextMessage textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public void run() {
        String jsonStr = "";
        MessageLog messageLog = new MessageLog();
        messageLog.setSendTime(StringUtil.dateToString(new Date()));
        try {
            jsonStr = textMessage.getText();
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            String templateNum = jsonObject.getString("templateNum");
            String phones = jsonObject.getString("phones");
            String clientNames = jsonObject.getString("clientNames");
            String signNames = jsonObject.getString("signNames");
            messageLog.setTemplateNum(templateNum);
            messageLog.setPhones(phones);
            messageLog.setClientNames(clientNames);
            messageLog.setSignName(signNames);

            String sendResult = AliyunUtil.sendInfo(templateNum, phones, clientNames, signNames);
            logger.info("发送短信mq, 接收数据 : " + jsonStr + ", 成功");
            messageLog.setSendResult(
                    null != AliyunCodeTypeEnum.getValueByKey(sendResult) ? AliyunCodeTypeEnum.getValueByKey(sendResult)
                            : sendResult);
            messageLog.setSendStatus(("ok".equals(sendResult)) ? 0 : 1);
        } catch (JMSException e) {
            messageLog.setSendStatus(1);
            messageLog.setSendResult(e.getMessage());
            logger.info("发送短信mq, 接收数据 : " + jsonStr + ", 业务异常, 失败", e);
        } catch (Exception e) {
            messageLog.setSendStatus(1);
            messageLog.setSendResult(e.getMessage());
            logger.info("发送短信mq, 接收数据 : " + jsonStr + ", 业务异常, 失败", e);
        }
        try {
            messageLogDao.insert(messageLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

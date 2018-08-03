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
import i9.defence.platform.utils.BusinessException;
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
        try {
            jsonStr = textMessage.getText();
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            String templateNum = jsonObject.getString("templateNum");
            String phones = jsonObject.getString("phones");
            String clientNames = jsonObject.getString("clientNames");
            String signNames = jsonObject.getString("signNames");
            String sendResult = AliyunUtil.sendInfo(templateNum, phones, clientNames, signNames); 
            logger.info("发送短信mq, 接收数据 : " + jsonStr + ", 成功");
            
            // TODO 增加数据记录，记录当前短信记录发送情况（编号，消息内容，发送人，状态【成功，失败】，发送时间）
            messageLog.setTemplateNum(templateNum);
            messageLog.setPhones(phones);
            messageLog.setClientNames(clientNames);
            messageLog.setSignName(signNames);
            messageLog.setSendResult(null !=AliyunCodeTypeEnum.getValueByKey(sendResult)?AliyunCodeTypeEnum.getValueByKey(sendResult):sendResult); 
        	messageLog.setSendStatus(("ok".equals(sendResult))?0:1);
        	messageLog.setSendTime(StringUtil.dateToString(new Date()));
        	messageLogDao.insert(messageLog); 
        } catch (JMSException e) {
            logger.info("发送短信mq, 接收数据 : " + jsonStr + ", 失败", e);
        } catch (Exception e) {
			throw new BusinessException("新增短信日志失败", e.getMessage());
		}
    }
}

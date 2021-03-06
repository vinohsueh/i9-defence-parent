package i9.defence.platform.microservice.mq.service.impl;

import i9.defence.platform.microservice.mq.service.ActiveMQConsumerTask;
import i9.defence.platform.microservice.mq.service.EquipmentCheckSendMessageService;
import i9.defence.platform.microservice.mq.service.EquipmentRecordService;
import i9.defence.platform.microservice.mq.util.SpringBeanService;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.mq.libraries.producer.ActiveMQProducerService;
import i9.defence.platform.service.UpStreamDecodeService;
import i9.defence.platform.utils.StringUtil;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 处理ActiveMQ业务消息处理任务
 * 
 * @author r12
 *
 */
public class ActiveMQBusinessConsumerTask extends ActiveMQConsumerTask {

    @Override
    public void run() {
        // 判断当前消息中是否有特定的数据通道（心跳，登录）
        if (checkDataMessageHaveGivenChannel()) {
            return;
        }
        UpStreamDecodeService upStreamDecodeService = getUpStreamDecodeService();
        try {
            int dataStatus = upStreamDecodeService.saveUpStreamDecode(textMessage.getText());
            if (dataStatus != 0) {
                JSONObject jsonObject = JSONObject.parseObject(textMessage.getText());
                String systemId = jsonObject.getString("systemId");
                int loop = jsonObject.getIntValue("loop");
                String address = jsonObject.getString("deviceAddress");
                String deviceId = StringUtil.getDeviceId(systemId, loop, address);

                EquipmentCheckSendMessageService equipmentCheckSendMessageService = SpringBeanService
                        .getBean(EquipmentCheckSendMessageService.class);
                equipmentCheckSendMessageService.checkEquipmentAndSendMessageAlarm(deviceId, dataStatus,
                        textMessage.getText());
            }
            logger.info("save up stream decode success, data : " + textMessage.getText());
            // 处理推送到第三方平台数据接口
            ActiveMQProducerService activeMQProducerService = SpringBeanService.getBean(ActiveMQProducerService.class);
            activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_PUSH, textMessage.getText());

            JSONObject jsonObject = JSONObject.parseObject(textMessage.getText());
            String systemId = jsonObject.getString("systemId");
            int loop = (Integer) jsonObject.get("loop");
            String address = jsonObject.getString("deviceAddress");
            String deviceId = StringUtil.getDeviceId(systemId, loop, address);
            EquipmentRecordService equipmentRecordService = SpringBeanService.getBean(EquipmentRecordService.class);
            equipmentRecordService.recordLastDate(deviceId);
        } catch (Exception e) {
            logger.error("save up stream decode error, ex : ", e);
        }
    }

    /**
     * 判断当前消息中是否有特定的数据通道（心跳，登录）
     * 
     * @return
     */
    public boolean checkDataMessageHaveGivenChannel() {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(textMessage.getText());
        } catch (JMSException e) {
            return false;
        }
        JSONArray dataList0 = jsonObject.getJSONArray("dataList");
        for (int index = 0; index < dataList0.size(); index++) {
            JSONObject dataItem = dataList0.getJSONObject(index);
            int channel = dataItem.getIntValue("channel");
            if (channel == -1 || channel == -2) {
                return true;
            }
        }
        return false;
    }

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQBusinessConsumerTask.class);

    private final TextMessage textMessage;

    public ActiveMQBusinessConsumerTask(TextMessage textMessage) {
        this.textMessage = textMessage;
    }
}

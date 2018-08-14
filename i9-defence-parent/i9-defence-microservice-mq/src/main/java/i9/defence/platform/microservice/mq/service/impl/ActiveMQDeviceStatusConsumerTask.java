package i9.defence.platform.microservice.mq.service.impl;

import java.util.Date;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.microservice.mq.service.ActiveMQConsumerTask;
import i9.defence.platform.microservice.mq.service.EquipmentCheckSendMessageService;
import i9.defence.platform.microservice.mq.util.SpringBeanService;
import i9.defence.platform.model.ConnectLog;
import i9.defence.platform.service.UpStreamDecodeService;
import i9.defence.platform.utils.DateUtils;
import i9.defence.platform.utils.StringUtil;

/**
 * 处理ActiveMQ设备连接消息通知任务
 * 
 * @author r12
 * 
 */
public class ActiveMQDeviceStatusConsumerTask extends ActiveMQConsumerTask {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQDeviceStatusConsumerTask.class);

    private final TextMessage textMessage;

    public ActiveMQDeviceStatusConsumerTask(TextMessage textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public void run() {
        UpStreamDecodeService upStreamDecodeService = getUpStreamDecodeService();
        try {
            JSONObject jsonObject = JSONObject.parseObject(textMessage.getText());

            String systemId = jsonObject.getString("systemId");
            int loop = jsonObject.getInteger("loop");
            String address = jsonObject.getString("deviceAddress");
            int status = jsonObject.getIntValue("status");
            String channelId = jsonObject.getString("channelId");

            // 通过设备唯一标识更新状态
            String deviceId = StringUtil.getDeviceId(systemId, loop, address);
            // int status = 1;
            upStreamDecodeService.updateEquipmentStatus(deviceId, status);
            logger.info("update device status success, deviceId : {}, status : {}", deviceId, status);

            // 插入离线掉线 记录
            ConnectLog connectLog = new ConnectLog();
            connectLog.setDeviceId(deviceId);// 设备唯一标识
            connectLog.setStatus(status);
            connectLog.setChannelId(channelId);

            String submitDate = jsonObject.getString("submitDate");
            if (submitDate == null || submitDate.equals("")) {
                // 兼容之前MQ消息
                connectLog.setCreateTime(new Date());// 时间
            } else {
                Date date = DateUtils.parseDate(submitDate);
                connectLog.setCreateTime(date);
            }

            upStreamDecodeService.insertConnectRecord(connectLog);

            // 发送离线短信
            if (status == 0) {
                EquipmentCheckSendMessageService equipmentCheckSendMessageService = SpringBeanService
                        .getBean(EquipmentCheckSendMessageService.class);
                equipmentCheckSendMessageService.checkEquipmentAndSendMessageOffline(deviceId);
            }
            logger.info("save connect log success, deviceId : {}, status : {}", deviceId, status);
        } catch (Exception e) {
            logger.error("save up stream decode error, ex : ", e);
        }
    }
}

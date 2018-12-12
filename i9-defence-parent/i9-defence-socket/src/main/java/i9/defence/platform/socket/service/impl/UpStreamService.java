package i9.defence.platform.socket.service.impl;

import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.mq.libraries.producer.ActiveMQProducerService;
import i9.defence.platform.netty.libraries.DataParseUtil;
import i9.defence.platform.netty.libraries.EncryptUtils;
import i9.defence.platform.netty.libraries.ErrorCode;
import i9.defence.platform.netty.libraries.req.DataMessage;
import i9.defence.platform.netty.libraries.req.UpStreamReqMessage;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.DeviceAttribute;
import i9.defence.platform.socket.exception.BusinessException;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;
import i9.defence.platform.socket.util.ChannelConnectedService;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public class UpStreamService implements ICoreService {

    @Autowired
    private ChannelConnectedService channelConnectedService;

    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        UpStreamReqMessage reqMessage = (UpStreamReqMessage) message.getMessageDecodeConvert();

        // 判断当前应用数据包中是否包含登录信息
        if (reqMessage.isLoginDataMessage()) {
            DeviceAttribute attribute = new DeviceAttribute(reqMessage.systemId, reqMessage.loop,
                    reqMessage.deviceAddress);
            channelPacker.putAttribute(attribute);
            channelConnectedService.connected(channelPacker);
            return;
        }

        // 判断当前应用数据包中是否包含心跳信息
        if (reqMessage.isHeartbeatDataMessage() && !channelPacker.checkLoginState()) {
            DeviceAttribute attribute = new DeviceAttribute(reqMessage.systemId, reqMessage.loop,
                    reqMessage.deviceAddress);
            channelPacker.putAttribute(attribute);
            channelConnectedService.connected(channelPacker);
            return;
        }

        // 设备要发送数据，必须先进行登录操作
        if (!channelPacker.checkLoginState()) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
        reqMessage.showInfo();
        for (DataMessage dataMessage : reqMessage.dataList) {
            dataMessage.showInfo();
            logger.info("解析数据包体, 数据类型 : " + dataMessage.type + ", hex : "
                    + EncryptUtils.bytesToHexString(dataMessage.data) + ", 值 : "
                    + DataParseUtil.parseDataValue(dataMessage.type, dataMessage.data));
        }
        // 清理上行数据
        JSONObject jsonObject = reqMessage.toJSONObject();
        this.cleanUpStreamData(jsonObject);

        jsonObject.put("channelId", channelPacker.getChannelId());
        String jsonStr = jsonObject.toJSONString();
        try {
            activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_BUSINESS, jsonStr);
            logger.info("发送到MQ服务器消息成功, jsonStr : " + jsonStr);
        } catch (Exception exception) {
            logger.error("发送到MQ服务器消息失败, jsonStr : " + jsonStr, exception);
        }
    }

    /**
     * 清理上行数据
     * 
     * @param reqMessage
     */
    private void cleanUpStreamData(JSONObject jsonObject) {
        String systemId = jsonObject.getString("systemId");
        if (!systemId.equals("000000000100")) {
            return;
        }
        logger.info("power, old : " + jsonObject.toJSONString());
        for (int index = 0; index < jsonObject.getJSONArray("dataList").size(); index++) {
            JSONObject item = jsonObject.getJSONArray("dataList").getJSONObject(index);
            byte channel = item.getByte("channel");
            if (channel == 19 || channel == 20 || channel == 21 || channel == 22) {
                Short value = item.getShort("value");
                item.put("value", this.setScaleFormat(value * 0.1f - 50));
            }
            if (channel == 23 || channel == 24 || channel == 25 || channel == 26 || channel == 27 || channel == 28) {
                Short value = item.getShort("value");
                item.put("value", this.setScaleFormat(value * 0.1f));
            }
            if (channel == 29 || channel == 30 || channel == 31) {
                Integer value = item.getInteger("value");
                item.put("value", this.setScaleFormat(value * 0.001f));
            }
            if (channel == 35 || channel == 36 || channel == 37) {
                Short value = item.getShort("value");
                item.put("value", this.setScaleFormat(value * 0.1f));
            }
            if (channel == 38 || channel == 39 || channel == 40) {
                Integer value = item.getInteger("value");
                item.put("value", this.setScaleFormat(value * 0.001f));
            }
            if (channel == 32 || channel == 33 || channel == 34) {
                Short value = EncryptUtils.parseUnsignedShort(item.getString("data"));
                item.put("value", value);
            }
        }
        logger.info("power, new : " + jsonObject.toJSONString());
    }

    private double setScaleFormat(double f) {
        BigDecimal bigDecimal = new BigDecimal(f);
        double r = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        return r;
    }

    private final static Logger logger = LoggerFactory.getLogger(UpStreamService.class);

    @Autowired
    private ActiveMQProducerService activeMQProducerService;
}

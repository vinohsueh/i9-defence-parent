package i9.defence.platform.socket.service.impl;

import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.mq.libraries.producer.ActiveMQProducerService;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.ChannelPackerServerContext;
import i9.defence.platform.socket.context.DeviceAttribute;
import i9.defence.platform.utils.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public class DisConnectionService {

    public void doPost(ChannelPacker channelPacker) {
        DeviceAttribute attribute = channelPacker.getAndRemoveAttribute();
        if (attribute != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("channelId", channelPacker.getChannelId());
            jsonObject.put("systemId", attribute.getSystemId());
            jsonObject.put("loop", attribute.getLoop());
            jsonObject.put("deviceAddress", attribute.getAddress());
            jsonObject.put("status", 0);
            jsonObject.put("submitDate", DateUtils.DateNowStr());
            jsonObject.put("channelId", channelPacker.getChannelId());
            activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_DEVICE_STATE, jsonObject.toJSONString());
        }
        else {
            logger.info("设备通道号 : " + channelPacker.getChannelId() + "未登录");
        }
        logger.info("netty 服务器，客户端断开连接 : " + channelPacker.getChannelId());
    }
    
    @Autowired
    private ActiveMQProducerService activeMQProducerService;
    
    private static final Logger logger = LoggerFactory.getLogger(HeartbeatService.class);
    
    @Autowired
    private ChannelPackerServerContext channelPackerServerContext;
}

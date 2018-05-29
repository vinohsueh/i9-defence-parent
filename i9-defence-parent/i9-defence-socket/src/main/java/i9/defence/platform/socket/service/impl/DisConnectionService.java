package i9.defence.platform.socket.service.impl;

import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.mq.libraries.producer.ActiveMQProducerService;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.ChannelPackerServerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public class DisConnectionService {

    public void doPost(ChannelPacker channelPacker) {
        String channelId = channelPacker.getChannelId();
        channelPackerServerContext.removeChannelPacker(channelId);
        if (channelPacker.checkIsLogin()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("channelId", channelPacker.getChannelId());
            jsonObject.put("systemId", channelPacker.systemId);
            jsonObject.put("loop", channelPacker.loop);
            jsonObject.put("deviceAddress", channelPacker.deviceAddress);
            jsonObject.put("status", 0);
            activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_DEVICE_STATE, jsonObject.toJSONString());
        }
        else {
            logger.info("设备通道号 : " + channelId + "未登录, systemId : {}, loop : {}, deviceAddress : {}", 
            		channelPacker.systemId, channelPacker.loop, channelPacker.deviceAddress);
        }
        channelPacker.disConnection();
        logger.info("netty 服务器，客户端断开连接 : " + channelId);
    }
    
    @Autowired
    private ActiveMQProducerService activeMQProducerService;
    
    private static final Logger logger = LoggerFactory.getLogger(HeartbeatService.class);
    
    @Autowired
    private ChannelPackerServerContext channelPackerServerContext;
}

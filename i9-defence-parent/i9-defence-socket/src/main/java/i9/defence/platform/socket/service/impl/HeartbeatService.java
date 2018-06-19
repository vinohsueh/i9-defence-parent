package i9.defence.platform.socket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.mq.libraries.producer.ActiveMQProducerService;
import i9.defence.platform.netty.libraries.req.HeartbeatReqMessage;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;
import i9.defence.platform.socket.util.StringUtil;

@Service
public class HeartbeatService implements ICoreService {

    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        HeartbeatReqMessage heartbeatReqMessage = (HeartbeatReqMessage) message.getMessageDecodeConvert();
        heartbeatReqMessage.showInfo();
        channelPacker.systemId = heartbeatReqMessage.systemId;
        channelPacker.deviceAddress = heartbeatReqMessage.deviceAddress;
        channelPacker.loop = heartbeatReqMessage.loop;
        if (channelPacker.isLogin.get() == false) {
	    	JSONObject jsonObject = new JSONObject();
	        jsonObject.put("channelId", channelPacker.getChannelId());
	        jsonObject.put("systemId", channelPacker.systemId);
	        jsonObject.put("loop", channelPacker.loop);
	        jsonObject.put("deviceAddress", channelPacker.deviceAddress);
	        jsonObject.put("status", 1);
            jsonObject.put("submitDate", StringUtil.DateNowStr());
	        activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_DEVICE_STATE, jsonObject.toJSONString());
        }
        channelPacker.isLogin.set(true);
    }
    
    @Autowired
    private ActiveMQProducerService activeMQProducerService;
    
//    private static final Logger logger = LoggerFactory.getLogger(HeartbeatService.class);
}

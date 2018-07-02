package i9.defence.platform.socket.service.impl;

import i9.defence.platform.netty.libraries.req.HeartbeatReqMessage;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.DeviceAttribute;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;
import i9.defence.platform.socket.util.ChannelConnectedService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeartbeatService implements ICoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatService.class);

    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        HeartbeatReqMessage heartbeatReqMessage = (HeartbeatReqMessage) message.getMessageDecodeConvert();
        heartbeatReqMessage.showInfo();
        if (heartbeatReqMessage.isDTU()) {
            LOGGER.info("设备判断为DTU，systemId : {}, loop : {}, address : {}", heartbeatReqMessage.systemId,
                    heartbeatReqMessage.loop, heartbeatReqMessage.deviceAddress);
            return;
        }

        if (!channelPacker.checkLoginState()) {
            DeviceAttribute attribute = new DeviceAttribute(heartbeatReqMessage.systemId, heartbeatReqMessage.loop,
                    heartbeatReqMessage.deviceAddress);
            channelPacker.putAttribute(attribute);
            channelConnectedService.connected(channelPacker);
        }
    }

    @Autowired
    private ChannelConnectedService channelConnectedService;
}

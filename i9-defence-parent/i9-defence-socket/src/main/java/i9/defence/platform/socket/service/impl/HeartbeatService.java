package i9.defence.platform.socket.service.impl;

import i9.defence.platform.netty.libraries.req.HeartbeatReqMessage;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.DeviceAttribute;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;
import i9.defence.platform.socket.util.ChannelConnectedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeartbeatService implements ICoreService {

    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        HeartbeatReqMessage heartbeatReqMessage = (HeartbeatReqMessage) message.getMessageDecodeConvert();
        heartbeatReqMessage.showInfo();

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

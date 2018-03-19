package i9.defence.platform.socket.service.impl;

import org.springframework.stereotype.Service;

import i9.defence.platform.netty.libraries.req.HeartbeatReqMessage;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;

@Service
public class HeartbeatService implements ICoreService {

    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        HeartbeatReqMessage heartbeatReqMessage = (HeartbeatReqMessage) message.getMessageDecodeConvert();
        heartbeatReqMessage.showInfo();
    }
    
//    private static final Logger logger = LoggerFactory.getLogger(HeartbeatService.class);
}

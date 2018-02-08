package i9.defence.platform.socket.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;

@Service
public class HeartbeatService implements ICoreService {

    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        logger.info("heartbeat channelId : " + channelPacker.getChannelId());
    }
    
    private static final Logger logger = LoggerFactory.getLogger(HeartbeatService.class);
}

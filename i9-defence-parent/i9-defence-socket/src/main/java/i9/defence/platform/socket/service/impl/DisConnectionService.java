package i9.defence.platform.socket.service.impl;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.ChannelPackerServerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisConnectionService {

    public void doPost(ChannelPacker channelPacker) {
        String channelId = channelPacker.getChannelId();
        channelPackerServerContext.removeChannelPacker(channelId);
        logger.info("netty 服务器，客户端断开连接 : " + channelId);
    }
    
    private static final Logger logger = LoggerFactory.getLogger(HeartbeatService.class);
    
    @Autowired
    private ChannelPackerServerContext channelPackerServerContext;
}

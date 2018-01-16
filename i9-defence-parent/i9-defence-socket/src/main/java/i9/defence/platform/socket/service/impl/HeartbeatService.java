package i9.defence.platform.socket.service.impl;

import org.springframework.stereotype.Service;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.service.ICoreService;

@Service
public class HeartbeatService implements ICoreService {

    @Override
    public void doPost(MessageDecodeConvert message, ChannelPacker channelPacker) {
    }
}

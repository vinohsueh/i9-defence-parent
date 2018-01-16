package i9.defence.platform.socket.service.impl;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.ChannelPackerServerContext;
import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.service.ICoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ICoreService {

    @Override
    public void doPost(MessageDecodeConvert message, ChannelPacker channelPacker) {
        channelPackerServerContext.addChannelPacker(channelPacker);
    }

    @Autowired
    private ChannelPackerServerContext channelPackerServerContext;
}

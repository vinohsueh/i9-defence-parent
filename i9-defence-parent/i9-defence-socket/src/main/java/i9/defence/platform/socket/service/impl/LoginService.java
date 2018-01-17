package i9.defence.platform.socket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.ChannelPackerServerContext;
import i9.defence.platform.socket.message.resp.CompleteRespMessage;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;

@Service
public class LoginService implements ICoreService {

    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        channelPackerServerContext.addChannelPacker(channelPacker);
        CompleteRespMessage completeRespMessage = new CompleteRespMessage(message.getType());
        message.setMessageEncodeConvert(completeRespMessage);
    }

    @Autowired
    private ChannelPackerServerContext channelPackerServerContext;
}

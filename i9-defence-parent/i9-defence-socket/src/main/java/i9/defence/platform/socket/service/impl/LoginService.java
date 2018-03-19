package i9.defence.platform.socket.service.impl;

import org.springframework.stereotype.Service;

import i9.defence.platform.netty.libraries.req.LoginReqMessage;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;

@Service
public class LoginService implements ICoreService {

    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        LoginReqMessage loginReqMessage = (LoginReqMessage) message.getMessageDecodeConvert();
        loginReqMessage.showInfo();
//        CompleteRespMessage completeRespMessage = new CompleteRespMessage(message.getType());
//        message.setMessageEncodeConvert(completeRespMessage);
    }
}

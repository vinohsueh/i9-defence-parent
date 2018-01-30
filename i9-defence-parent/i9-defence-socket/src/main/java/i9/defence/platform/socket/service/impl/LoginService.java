package i9.defence.platform.socket.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.ChannelPackerServerContext;
import i9.defence.platform.socket.message.req.LoginReqMessage;
import i9.defence.platform.socket.message.resp.CompleteRespMessage;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;
import i9.defence.platform.socket.util.AES256;
import i9.defence.platform.socket.util.EncryptUtils;

@Service
public class LoginService implements ICoreService {

    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        channelPackerServerContext.addChannelPacker(channelPacker);
        LoginReqMessage loginReqMessage = (LoginReqMessage) message.getMessageDecodeConvert();
        loginReqMessage.showInfo();
        
        String password = "00000000";
        byte[] devEUI = AES256.encrypt(loginReqMessage.data, password.getBytes());
        logger.info("login channelId : " + channelPacker.getChannelId() + ", DevEUI : " + EncryptUtils.bytesToHexString(devEUI));
        CompleteRespMessage completeRespMessage = new CompleteRespMessage(message.getType());
        message.setMessageEncodeConvert(completeRespMessage);
    }
    
    private static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private ChannelPackerServerContext channelPackerServerContext;
}

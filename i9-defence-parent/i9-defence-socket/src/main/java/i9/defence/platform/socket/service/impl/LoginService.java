package i9.defence.platform.socket.service.impl;

import i9.defence.platform.netty.libraries.req.LoginReqMessage;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.context.DeviceAttribute;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;
import i9.defence.platform.socket.util.ChannelConnectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ICoreService {

    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        LoginReqMessage loginReqMessage = (LoginReqMessage) message.getMessageDecodeConvert();
        loginReqMessage.showInfo();

        DeviceAttribute attribute = new DeviceAttribute(loginReqMessage.systemId, loginReqMessage.loop,
                loginReqMessage.deviceAddress);
        channelPacker.putAttribute(attribute);
        channelConnectedService.connected(channelPacker);
    }

    @Autowired
    private ChannelConnectedService channelConnectedService;
}

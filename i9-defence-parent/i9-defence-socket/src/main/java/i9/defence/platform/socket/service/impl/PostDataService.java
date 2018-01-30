package i9.defence.platform.socket.service.impl;

import org.springframework.stereotype.Service;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.message.req.DataMessage;
import i9.defence.platform.socket.message.req.UplinkReqMessage;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.ICoreService;

@Service
public class PostDataService implements ICoreService {

    @Override
    public void doPost(Message message, ChannelPacker channelPacker) {
        UplinkReqMessage uplinkReqMessage = (UplinkReqMessage) message.getMessageDecodeConvert();
        uplinkReqMessage.showInfo();
        for (DataMessage dataMessage : uplinkReqMessage.data) {
            dataMessage.showInfo();
        }
    }
}

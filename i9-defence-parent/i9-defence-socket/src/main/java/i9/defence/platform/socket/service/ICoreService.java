package i9.defence.platform.socket.service;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.message.MessageDecodeConvert;

public interface ICoreService {

    public void doPost(MessageDecodeConvert message, ChannelPacker channelPacker);
}

package i9.defence.platform.socket.service;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.netty.Message;

public interface ICoreService {

    public void doPost(Message message, ChannelPacker channelPacker);
}

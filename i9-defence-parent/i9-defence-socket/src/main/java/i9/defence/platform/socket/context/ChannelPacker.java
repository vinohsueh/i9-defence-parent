package i9.defence.platform.socket.context;

import java.util.concurrent.atomic.AtomicInteger;

import i9.defence.platform.socket.message.MessageEncodeConvert;
import i9.defence.platform.socket.message.RespMessageBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class ChannelPacker {

    private Channel channel;
    
    public ChannelPacker(Channel channel) {
        this.channel = channel;
    }
    
    public String getChannelId() {
        return this.channel.id().asLongText();
    }

    public void writeAndFlush(MessageEncodeConvert messageEncodeConvert) {
        int index = next();
        ByteBuf buf = RespMessageBuilder.wrapper(messageEncodeConvert, index);
        this.channel.writeAndFlush(buf);
    }
    
    private AtomicInteger index = new AtomicInteger(1);
    
    public int next() {
        return index.getAndIncrement();
    }
}

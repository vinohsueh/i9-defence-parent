package i9.defence.platform.socket.context;

import i9.defence.platform.netty.libraries.MessageEncodeConvert;
import i9.defence.platform.netty.libraries.RespMessageBuilder;
import i9.defence.platform.socket.util.StringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class ChannelPacker {
    
    public ChannelPacker() {
    }

    private Channel channel;
    
    public ChannelPacker(Channel channel) {
        this.channel = channel;
    }
    
    public String getChannelId() {
        if (this.channel == null) {
            return StringUtil.EMPTY;
        }
        return this.channel.id().asLongText();
    }

    public void writeAndFlush(MessageEncodeConvert messageEncodeConvert, int index) {
        ByteBuf buf = RespMessageBuilder.wrapper(messageEncodeConvert, index);
        this.channel.writeAndFlush(buf);
    }
}

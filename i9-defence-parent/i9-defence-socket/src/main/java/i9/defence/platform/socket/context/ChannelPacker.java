package i9.defence.platform.socket.context;

import io.netty.channel.Channel;

public class ChannelPacker {

    private Channel channel;
    
    public ChannelPacker(Channel channel) {
        this.channel = channel;
    }
    
    public String getChannelId() {
        return this.channel.id().asLongText();
    }
}

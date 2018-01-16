package i9.defence.platform.socket.message;

import io.netty.buffer.ByteBuf;

public interface MessageEncodeConvert {

    public ByteBuf encode();
    
    public byte getType();
}

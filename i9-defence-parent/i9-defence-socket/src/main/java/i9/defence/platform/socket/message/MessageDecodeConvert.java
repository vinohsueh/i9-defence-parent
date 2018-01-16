package i9.defence.platform.socket.message;

import io.netty.buffer.ByteBuf;


public interface MessageDecodeConvert {

    public void decode(ByteBuf buf);
    
    public byte getType();
    
    public void setType(byte type);
}

package i9.defence.platform.netty.libraries;

import io.netty.buffer.ByteBuf;

public abstract class MessageEncodeConvert {

    public abstract ByteBuf encode();
    
    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }
    
    private byte type;
}

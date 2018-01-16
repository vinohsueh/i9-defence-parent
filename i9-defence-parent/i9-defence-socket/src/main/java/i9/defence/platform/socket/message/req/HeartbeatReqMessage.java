package i9.defence.platform.socket.message.req;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import io.netty.buffer.ByteBuf;

public class HeartbeatReqMessage implements MessageDecodeConvert {

    @Override
    public void decode(ByteBuf buf) {
    }

    @Override
    public byte getType() {
        return type;
    }
    
    public byte type;

    @Override
    public void setType(byte type) {
        this.type = type;
    }
}

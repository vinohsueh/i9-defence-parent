package i9.defence.platform.socket.message;

import io.netty.buffer.ByteBuf;


public abstract class MessageDecodeConvert {

    public abstract void decode(ByteBuf buf);
}

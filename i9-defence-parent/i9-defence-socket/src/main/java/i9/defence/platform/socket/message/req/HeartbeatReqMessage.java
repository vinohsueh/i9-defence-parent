package i9.defence.platform.socket.message.req;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import io.netty.buffer.ByteBuf;

public class HeartbeatReqMessage extends MessageDecodeConvert {

    @Override
    public boolean decode(ByteBuf buf) {
        return false;
    }

    @Override
    public byte[] getByteArray() {
        // TODO Auto-generated method stub
        return null;
    }
}

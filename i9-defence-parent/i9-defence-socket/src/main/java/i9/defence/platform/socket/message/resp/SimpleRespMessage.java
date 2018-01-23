package i9.defence.platform.socket.message.resp;

import i9.defence.platform.socket.message.MessageEncodeConvert;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class SimpleRespMessage extends MessageEncodeConvert {

    public int result;

    public SimpleRespMessage(byte type, int result) {
        this.result = result;
        this.setType(type);
    }

    @Override
    public ByteBuf encode() {
        ByteBuf buf = Unpooled.buffer(2);
        buf.writeByte((byte) 1);
        buf.writeByte((byte) result);
        return buf;
    }
}

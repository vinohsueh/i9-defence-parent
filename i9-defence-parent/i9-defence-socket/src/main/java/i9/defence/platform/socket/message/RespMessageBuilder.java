package i9.defence.platform.socket.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class RespMessageBuilder {

    public static ByteBuf wrapper(MessageEncodeConvert messageEncodeConvert, int index) {
        byte[] data = messageEncodeConvert.encode().array();
        ByteBuf buf = Unpooled.buffer(8 + data.length + 2);
        buf.writeByte(0x40);
        buf.writeByte(0x10);
        buf.writeByte(messageEncodeConvert.getType());
        buf.writeInt(index);
        buf.writeByte(data.length);
        buf.writeBytes(data);
        buf.writeByte(1);
        buf.writeByte(0x23);
        return buf;
    }
}

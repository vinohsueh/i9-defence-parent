package i9.defence.platform.netty.libraries;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class RespMessageBuilder {

    public static ByteBuf wrapper(MessageEncodeConvert messageEncodeConvert, int index) {
        byte[] data = messageEncodeConvert.encode().array();
        ByteBuf buf = Unpooled.buffer(7 + data.length + 2);
        buf.writeByte(0x40);
        
        ByteBuffer byteBuffer = ByteBuffer.allocate(6 + data.length);
        byteBuffer.put((byte) 0x10);
        byteBuffer.put(messageEncodeConvert.getType());
        byteBuffer.putInt(index);
//        byteBuffer.put((byte) data.length);
        byteBuffer.put(data);
        
        byte[] mByte = byteBuffer.array();
        buf.writeBytes(mByte);
        
        buf.writeByte(EncryptUtils.SumCheck(mByte)[7]);
        buf.writeByte(0x23);
        return buf;
    }
}

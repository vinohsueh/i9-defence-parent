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
        if (messageEncodeConvert.getType() == 0x00 || messageEncodeConvert.getType() == 0xff
                || messageEncodeConvert.getType() == -1) {
            byteBuffer.put(messageEncodeConvert.getType());
        } else {
            byteBuffer.put((byte) 0x04);
        }
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

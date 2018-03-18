package i9.defence.platform.netty.libraries.resp;

import i9.defence.platform.netty.libraries.ErrorCode;
import i9.defence.platform.netty.libraries.MessageEncodeConvert;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class CompleteRespMessage extends MessageEncodeConvert {
    
    public CompleteRespMessage(byte type) {
        this.setType(type);
    }
    
    @Override
    public ByteBuf encode() {
        ByteBuf buf = Unpooled.buffer(2);
        buf.writeByte((byte) 1);
        buf.writeByte((byte) ErrorCode.SUCCESS);
        return buf;
    }
}

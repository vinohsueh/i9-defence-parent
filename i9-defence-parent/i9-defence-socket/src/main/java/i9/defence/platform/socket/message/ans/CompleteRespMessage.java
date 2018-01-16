package i9.defence.platform.socket.message.ans;

import i9.defence.platform.socket.message.MessageEncodeConvert;
import i9.defence.platform.socket.util.ErrorCode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class CompleteRespMessage implements MessageEncodeConvert {
    
    public CompleteRespMessage(byte type) {
        this.type = type;
    }
    
    private byte type;

    @Override
    public ByteBuf encode() {
        ByteBuf buf = Unpooled.buffer(2);
        buf.writeByte((byte) 1);
        buf.writeByte((byte) ErrorCode.SUCCESS);
        return buf;
    }

    @Override
    public byte getType() {
        return type;
    }
}

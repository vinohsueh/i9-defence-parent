package i9.defence.platform.socket.message.resp;

import i9.defence.platform.socket.message.MessageEncodeConvert;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class HeartbeatRespMessage extends MessageEncodeConvert {

    @Override
    public ByteBuf encode() {
        ByteBuf byteBuf = Unpooled.buffer(5);
        byteBuf.writeByte((byte) 4);
        byteBuf.writeInt(this.timestamp);
        return byteBuf;
    }
    
    private int timestamp;

    public HeartbeatRespMessage(int timestamp) {
        this.timestamp = timestamp;
    }
}

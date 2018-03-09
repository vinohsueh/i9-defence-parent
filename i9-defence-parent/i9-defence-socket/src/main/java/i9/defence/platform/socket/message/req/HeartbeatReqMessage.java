package i9.defence.platform.socket.message.req;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import io.netty.buffer.ByteBuf;

public class HeartbeatReqMessage extends MessageDecodeConvert {
    
    public void showInfo() {
        logger.info("解码, 数据长度 : {}, 时间戳 : {}", 4, this.timestamp);
    }
    
    private final static Logger logger = LoggerFactory.getLogger(HeartbeatReqMessage.class);

    @Override
    public boolean decode(ByteBuf buf) {
        if (buf.readableBytes() < 5) {
            return true;
        }
        buf.readByte();
        this.timestamp = buf.readInt();
        return false;
    }
    
    public int timestamp;

    @Override
    public byte[] getByteArray() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        byteBuffer.put((byte) 4);
        byteBuffer.putInt(this.timestamp);
        return byteBuffer.array();
    }

    @Override
    public JSONObject toJSONObject() {
        return null;
    }
}

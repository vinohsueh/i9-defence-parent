package i9.defence.platform.socket.message.req;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.util.EncryptUtils;
import io.netty.buffer.ByteBuf;

public class HeartbeatReqMessage extends MessageDecodeConvert {
    
    public void showInfo() {
        logger.info("解码, 数据长度 : {}, 时间戳 : {}", 6, EncryptUtils.bytesToHexString(this.timestamp));
    }
    
    private final static Logger logger = LoggerFactory.getLogger(HeartbeatReqMessage.class);

    @Override
    public boolean decode(ByteBuf buf) {
        if (buf.readableBytes() < 7) {
            return true;
        }
        buf.readByte();
        timestamp = new byte[6];
        buf.readBytes(timestamp);
        return false;
    }
    
    public byte[] timestamp;

    @Override
    public byte[] getByteArray() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(7);
        byteBuffer.put((byte) 6);
        byteBuffer.put(this.timestamp);
        return byteBuffer.array();
    }

    @Override
    public JSONObject toJSONObject() {
        return null;
    }
}

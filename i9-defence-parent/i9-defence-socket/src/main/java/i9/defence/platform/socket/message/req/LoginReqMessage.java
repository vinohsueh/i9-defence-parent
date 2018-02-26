package i9.defence.platform.socket.message.req;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.util.EncryptUtils;
import io.netty.buffer.ByteBuf;

public class LoginReqMessage extends MessageDecodeConvert {

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dataLen", dataLen);
        jsonObject.put("data", EncryptUtils.bytesToHexString(this.data));
        return jsonObject;
    }
    
    public byte dataLen;
    
    public byte[] data;

    @Override
    public boolean decode(ByteBuf buf) {
        if (buf.readableBytes() < 1) {
            return true;
        }
        this.dataLen = buf.readByte();
        if (buf.readableBytes() < this.dataLen) {
            return true;
        }
        this.data = new byte[this.dataLen];
        buf.readBytes(this.data);
        return false;
    }

    public void showInfo() {
        logger.info("解码, [数据长度 : {}, 数据 : {}]", this.dataLen, EncryptUtils.bytesToHexString(this.data));
    }
    
    private static final Logger logger = LoggerFactory.getLogger(LoginReqMessage.class);

    @Override
    public byte[] getByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(dataLen + 1);
        buffer.put(this.dataLen);
        buffer.put(this.data);
        return buffer.array();
    }
}

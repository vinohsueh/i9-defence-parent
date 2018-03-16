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
        logger.info("解码心跳, [数据长度 : {}, 系统编号 : {}, 回路号 : {}, 设备地址 : {}]", this.dataLen, this.systemId, this.loop, this.deviceAddress);
    }
    
    private final static Logger logger = LoggerFactory.getLogger(HeartbeatReqMessage.class);

    @Override
    public boolean decode(ByteBuf buf) {
        if (buf.readableBytes() < 1) {
            return true;
        }
        this.dataLen = buf.readByte();
        if (buf.readableBytes() < this.dataLen) {
            return true;
        }
        byte[] dst = new byte[6];
        buf.readBytes(dst);
        this.systemId = EncryptUtils.bytesToHexString(dst);
        
        this.loop = buf.readByte();
        
        dst = new byte[4];
        buf.readBytes(dst);
        this.deviceAddress = EncryptUtils.bytesToHexString(dst);
        
        return false;
    }
    
    public byte dataLen;
    
    public String systemId;// 系统编号(十六进制)
    
    public byte loop;// 回路
    
    public String deviceAddress;// 设备地址
    

    @Override
    public byte[] getByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(dataLen + 1);
        buffer.put(this.dataLen);
        buffer.put(EncryptUtils.hexStringToBytes(this.systemId));
        buffer.put(this.loop);
        buffer.put(EncryptUtils.hexStringToBytes(this.deviceAddress));
        return buffer.array();
    }

    @Override
    public JSONObject toJSONObject() {
        return null;
    }
}

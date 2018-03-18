package i9.defence.platform.netty.libraries.req;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.netty.libraries.DataParseUtil;
import i9.defence.platform.netty.libraries.EncryptUtils;
import i9.defence.platform.netty.libraries.MessageDecodeConvert;
import io.netty.buffer.ByteBuf;

public class DataMessage extends MessageDecodeConvert {

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channel", this.channel);
        jsonObject.put("dataType", this.dataType);
        jsonObject.put("datetime", this.datetime);
        jsonObject.put("dataLen", this.dataLen);
        jsonObject.put("data", EncryptUtils.bytesToHexString(this.data));
        jsonObject.put("value", DataParseUtil.parseDataValue(this.dataType, this.data));
        return jsonObject;
    }

    public byte channel;
    
    public short dataType;
    
    public String datetime;
    
    public byte dataLen;
    
    public byte[] data;
    
    public byte Y;
    
    public byte M;
    
    public byte D;
    
    public byte H;
    
    public byte m;
    
    public byte S;

    @Override
    public boolean decode(ByteBuf buf) {
        if (buf.readableBytes() < 1 + 2 + 6 + 1) {
            return true;
        }
        this.channel = buf.readByte();
        this.dataType = buf.readShort();
        byte[] dst = new byte[6];
        buf.readBytes(dst);
        this.datetime  = String.format("%02d-%02d-%02d#%02d:%02d:%02d", dst[0], dst[1], dst[2], dst[3], dst[4], dst[5]);
        this.Y = dst[0];
        this.M = dst[1];
        this.D = dst[2];
        this.H = dst[3];
        this.m = dst[4];
        this.S = dst[5];
        this.dataLen = buf.readByte();
        if (buf.readableBytes() < this.dataLen) {
            return true;
        }
        this.data = new byte[this.dataLen];
        buf.readBytes(this.data);
        return false;
    }
    
    private final static Logger logger = LoggerFactory.getLogger(DataMessage.class);

    public void showInfo() {
        logger.info("解码, 设备通道 : {}, 数据类型 : {}, 产生时间 : {}, 数据长度 : {}, 数据 : {}", 
                this.channel, this.dataType, this.datetime, this.dataLen, EncryptUtils.bytesToHexString(this.data));
    }
    
    @Override
    public byte[] getByteArray() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1 + 2 + 6 + 1 + this.data.length);
        byteBuffer.put(this.channel);
        byteBuffer.putShort(this.dataType);
        byteBuffer.put(this.Y);
        byteBuffer.put(this.M);
        byteBuffer.put(this.D);
        byteBuffer.put(this.H);
        byteBuffer.put(this.m);
        byteBuffer.put(this.S);
        byteBuffer.put(this.dataLen);
        byteBuffer.put(this.data);
        return byteBuffer.array();
    }
}

package i9.defence.platform.netty.libraries.req;

import i9.defence.platform.netty.libraries.DataParseUtil;
import i9.defence.platform.netty.libraries.DateUtil;
import i9.defence.platform.netty.libraries.EncryptUtils;
import i9.defence.platform.netty.libraries.MessageDecodeConvert;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class DataMessage extends MessageDecodeConvert {

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channel", this.channelId);
        jsonObject.put("source", this.source);
        jsonObject.put("type", this.type);
        jsonObject.put("datetime", this.datetime);
        jsonObject.put("len", this.len);
        jsonObject.put("data", EncryptUtils.bytesToHexString(this.data));
        jsonObject.put("value", DataParseUtil.parseDataValue(this.type, this.data));
        return jsonObject;
    }

    public byte channelId;
    
    public byte source;
    
    public byte type;
    
    public String datetime;
    
    public byte len;
    
    public byte[] data;
    
    public byte[] nbs;

    @Override
    public boolean decode(ByteBuf buf) {
        if (buf.readableBytes() < 1 + 2 + 6 + 1) {
            return true;
        }
        this.channelId = buf.readByte();
        this.source = buf.readByte();
        this.type = buf.readByte();
        nbs = new byte[6];
        buf.readBytes(nbs);
        if (nbs[0] == (byte) -1) {
            this.datetime = DateUtil.format(new Date());
        }
        else {
            this.datetime = String.format("%02d-%02d-%02d#%02d:%02d:%02d", 2000 + nbs[0], nbs[1], nbs[2], nbs[3],
                    nbs[4], nbs[5]);
        }
        this.len = buf.readByte();
        if (buf.readableBytes() < this.len) {
            return true;
        }
        logger.info("data message len : " + this.len);
        this.data = new byte[this.len];
        buf.readBytes(this.data);
        return false;
    }
    
    private final static Logger logger = LoggerFactory.getLogger(DataMessage.class);

    public void showInfo() {
        logger.info("解码, 设备通道 : {}, 数据类型 : {}, 产生时间 : {}, 数据长度 : {}, 数据 : {}", 
                this.channelId, this.type, this.datetime, this.len, EncryptUtils.bytesToHexString(this.data));
    }
    
    @Override
    public byte[] getByteArray() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1 + 2 + 6 + 1 + this.data.length);
        byteBuffer.put(this.channelId);
        byteBuffer.put(this.source);
        byteBuffer.put(this.type);
        byteBuffer.put(this.nbs);
        byteBuffer.put(this.len);
        byteBuffer.put(this.data);
        return byteBuffer.array();
    }
}

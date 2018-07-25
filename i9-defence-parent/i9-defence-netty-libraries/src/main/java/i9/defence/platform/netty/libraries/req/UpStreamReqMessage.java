package i9.defence.platform.netty.libraries.req;

import i9.defence.platform.netty.libraries.MessageDecodeConvert;
import i9.defence.platform.utils.DateUtils;
import i9.defence.platform.utils.EncryptUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class UpStreamReqMessage extends MessageDecodeConvert {
    
    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("systemType", systemType);
        jsonObject.put("systemId", systemId);
        jsonObject.put("source", source);
        jsonObject.put("loop", loop);
        jsonObject.put("deviceAddress", deviceAddress);
        jsonObject.put("unit", unit);
        jsonObject.put("dataLen", dataLen);
        JSONArray tmpList = new JSONArray();
        for (DataMessage dataMessage : this.dataList) {
            tmpList.add(dataMessage.toJSONObject());
        }
        jsonObject.put("dataList", tmpList);
        jsonObject.put("submitDate", DateUtils.DateNowStr());
        return jsonObject;
    }
    
    public boolean isLoginDataMessage() {
        for (DataMessage dataMessage : this.dataList) {
            if (EncryptUtils.byteToPositive(dataMessage.channelId) == 255) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isHeartbeatDataMessage() {
        for (DataMessage dataMessage : this.dataList) {
            if (EncryptUtils.byteToPositive(dataMessage.channelId) == 254) {
                return true;
            }
        }
        return false;
    }

    public String systemType;// 系统类型(十六进制)

    public String systemId;// 系统编号(十六进制)

    public byte source;// 来源使用情况

    public byte loop;// 回路

    public String deviceAddress;// 设备地址

    public byte unit;

    public short dataLen;

    public List<DataMessage> dataList = new ArrayList<DataMessage>();
    
    @Override
    public boolean decode(ByteBuf buf) {
        if (buf.readableBytes() < 2 + 1 + 4 + 1 + 1 + 6 + 2) {
            return true;
        }
        {
            byte[] dst = new byte[2];
            buf.readBytes(dst);
            this.systemType = EncryptUtils.bytesToHexString(dst);
        }
        {
            byte[] dst = new byte[6];
            buf.readBytes(dst);
            this.systemId = EncryptUtils.bytesToHexString(dst);
        }
        this.source = buf.readByte();
        this.loop = buf.readByte();
        {
            byte[] dst = new byte[4];
            buf.readBytes(dst);
            this.deviceAddress = EncryptUtils.bytesToHexString(dst);
        }
        this.unit = buf.readByte();
        this.dataLen = buf.readShort();
        for (char c = 0; c < unit; c++) {
            DataMessage dataMessage = new DataMessage();
            boolean can = dataMessage.decode(buf);
            if (can) {
                return true;
            }
            this.dataList.add(dataMessage);
        }
        return false;
    }
    
    public void showInfo() {
        logger.info("解码上行数据, 系统类型 : {}, 系统编号 : {}, 来源使用情况 : {}, 回路 : {}, 设备地址 : {}, 数据单元数 : {}, 数据总长度 : {}", 
                this.systemType, this.systemId, this.source, this.loop, this.deviceAddress, this.unit, this.dataLen);
    }

    private final static Logger logger = LoggerFactory.getLogger(UpStreamReqMessage.class);

    @Override
    public byte[] getByteArray() {
        int len = 0;
        for (DataMessage dataMessage : this.dataList) {
            len += dataMessage.getByteArray().length;
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(2 + 1 + 4 + 1 + 1 + 6 + 2 + len);
        byte[] b0 = EncryptUtils.hexStringToBytes(this.systemType);
        byteBuffer.put(b0);
        byte[] b = EncryptUtils.hexStringToBytes(this.systemId);
        byteBuffer.put(b);
        byteBuffer.put(this.source);
        byteBuffer.put(this.loop);
        
        b = EncryptUtils.hexStringToBytes(this.deviceAddress);
        byteBuffer.put(b);
        byteBuffer.put(this.unit);
        byteBuffer.putShort(this.dataLen);
        
        for (DataMessage dataMessage : this.dataList) {
            byteBuffer.put(dataMessage.getByteArray());
        }
        return byteBuffer.array();
    }
}

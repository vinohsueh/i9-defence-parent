package i9.defence.platform.socket.message.req;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.util.EncryptUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UplinkReqMessage extends MessageDecodeConvert {

    public short systemType;// 系统类型(十六进制)

    public String systemId;// 系统编号(十六进制)

    public byte source;// 来源使用情况

    public byte loop;// 回路

    public String deviceAddress;// 设备地址

    public byte unit;

    public short dataLen;

    public List<DataMessage> data = new ArrayList<DataMessage>();
    
    public byte type;
    
    @Override
    public boolean decode(ByteBuf buf) {
        if (buf.readableBytes() < 2 + 1 + 4 + 1 + 1 + 6 + 2) {
            return true;
        }
        this.systemType = buf.readShort();
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
        for (char c = 0; c < dataLen; c++) {
            DataMessage dataMessage = new DataMessage();
            boolean can = dataMessage.decode(buf);
            if (can) {
                return true;
            }
            this.data.add(dataMessage);
        }
        return false;
    }
    
    public void showInfo() {
        logger.info("解码, [系统类型 : {}, 系统编号 : {}, 来源使用情况 : {}, 回路 : {}, 设备地址 : {}, 数据单元数 : {}, 数据总长度 : {}]", 
                this.systemType, this.systemId, this.source, this.loop, this.deviceAddress, this.unit, this.dataLen);
    }

    private final static Logger logger = LoggerFactory.getLogger(UplinkReqMessage.class);

    @Override
    public byte[] getByteArray() {
        int len = 0;
        for (DataMessage dataMessage : this.data) {
            len += dataMessage.getByteArray().length;
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(2 + 1 + 4 + 1 + 1 + 6 + 2 + len);
        byteBuffer.putShort(this.systemType);
        byte[] b = EncryptUtils.hexStringToBytes(this.systemId);
        byteBuffer.put(b);
        byteBuffer.put(this.source);
        byteBuffer.put(this.loop);
        
        b = EncryptUtils.hexStringToBytes(this.deviceAddress);
        byteBuffer.put(b);
        byteBuffer.put(this.unit);
        byteBuffer.putShort(this.dataLen);
        
        for (DataMessage dataMessage : this.data) {
            byteBuffer.put(dataMessage.getByteArray());
        }
        return byteBuffer.array();
    }
}

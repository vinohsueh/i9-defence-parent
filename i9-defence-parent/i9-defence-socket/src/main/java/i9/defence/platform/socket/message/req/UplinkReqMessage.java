package i9.defence.platform.socket.message.req;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.util.EncryptUtils;

public class UplinkReqMessage implements MessageDecodeConvert {

    public String systemType;// 系统类型(十六进制)

    public String systemId;// 系统编号(十六进制)

    public byte source;// 来源使用情况

    public byte loop;// 回路

    public String deviceAddress;// 设备地址

    public byte unit;

    public char dataLen;

    public List<DataMessage> data = new ArrayList<DataMessage>();

    @Override
    public void decode(ByteBuffer byteBuffer) {
        {
            byte[] dst = new byte[2];
            byteBuffer.get(dst);
            this.systemType = EncryptUtils.bytesToHexString(dst);
        }
        {
            byte[] dst = new byte[6];
            byteBuffer.get(dst);
            this.systemId = EncryptUtils.bytesToHexString(dst);
        }
        this.source = byteBuffer.get();
        this.loop = byteBuffer.get();
        {
            byte[] dst = new byte[4];
            byteBuffer.get(dst);
            this.deviceAddress = EncryptUtils.bytesToHexString(dst);
        }
        this.unit = byteBuffer.get();
        this.dataLen = byteBuffer.getChar();
        this.printInfo();
        for (char c = 0; c < dataLen; c++) {
            DataMessage dataMessage = new DataMessage();
            dataMessage.decode(byteBuffer);
            this.data.add(dataMessage);
        }
    }
    
    public void printInfo() {
        logger.info("解码, [系统类型 : {}, 系统编号 : {}, 来源使用情况 : {}, 回路 : {}, 设备地址 : {}, 数据单元数 : {}, 数据总长度 : {}]", 
                this.systemType, this.systemId, this.source, this.loop, this.deviceAddress, this.unit, this.dataLen);
    }

    private final static Logger logger = LoggerFactory.getLogger(UplinkReqMessage.class);
}

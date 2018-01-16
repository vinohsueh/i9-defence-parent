package i9.defence.platform.socket.message.req;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.util.EncryptUtils;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataMessage implements MessageDecodeConvert {

    public byte channel;
    
    public char type;
    
    public String datetime;
    
    public byte len;
    
    public byte[] data;

    @Override
    public void decode(ByteBuffer byteBuffer) {
        this.channel = byteBuffer.get();
        this.type = byteBuffer.getChar();
        byte[] dst = new byte[6];
        byteBuffer.get(dst);
        this.datetime = dst[0] + "-" + dst[1] + "-" + dst[2] + "#" + dst[3] + ":" + dst[4] + ":" + dst[5];
        this.len = byteBuffer.get();
        this.data = new byte[this.len];
        byteBuffer.get(this.data);
        this.printInfo();
    }
    
    private final static Logger logger = LoggerFactory.getLogger(DataMessage.class);

    public void printInfo() {
        logger.info("解码, 设备通道 : {}, 数据类型 : {}, 产生时间 : {}, 数据长度 : {}, 数据 : {}", 
                this.channel, this.type, this.datetime, this.len, EncryptUtils.bytesToHexString(this.data));
    }
}

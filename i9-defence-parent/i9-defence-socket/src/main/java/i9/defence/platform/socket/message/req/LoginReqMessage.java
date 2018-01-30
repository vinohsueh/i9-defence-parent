package i9.defence.platform.socket.message.req;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.util.EncryptUtils;
import io.netty.buffer.ByteBuf;

public class LoginReqMessage extends MessageDecodeConvert {
    
    public byte dataLen;
    
    public byte[] data;

    @Override
    public void decode(ByteBuf buf) {
        this.dataLen = buf.readByte();
        this.data = new byte[this.dataLen];
        buf.readBytes(this.data);
    }

    public void showInfo() {
        logger.info("解码, [数据长度 : {}, 数据 : {}]", this.dataLen, EncryptUtils.bytesToHexString(this.data));
    }
    
    private static final Logger logger = LoggerFactory.getLogger(LoginReqMessage.class);
}

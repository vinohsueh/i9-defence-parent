package i9.defence.platform.socket.message.req;

import java.nio.ByteBuffer;

import i9.defence.platform.socket.message.MessageDecodeConvert;

public class LoginReqMessage implements MessageDecodeConvert {
    
    public byte dataLen;
    
    public byte[] data;

    @Override
    public void decode(ByteBuffer byteBuffer) {
        this.dataLen = byteBuffer.get();
        this.data = new byte[this.dataLen];
        byteBuffer.get(this.data);
    }
}

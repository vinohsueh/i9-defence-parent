package i9.defence.platform.socket.message.req;

import i9.defence.platform.socket.message.MessageDecodeConvert;

import java.nio.ByteBuffer;

public class DataMessage implements MessageDecodeConvert {

    private byte channel;
    
    private char type;
    
    private String datetime;
    
    public String getDatetime() {
        return datetime;
    }

    private byte len;
    
    private byte[] data;

    public byte getChannel() {
        return channel;
    }

    public char getType() {
        return type;
    }

    public byte getLen() {
        return len;
    }

    public byte[] getData() {
        return data;
    }

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
    }
}

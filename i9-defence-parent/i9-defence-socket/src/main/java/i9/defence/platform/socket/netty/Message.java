package i9.defence.platform.socket.netty;

import i9.defence.platform.netty.libraries.MessageDecodeConvert;
import i9.defence.platform.netty.libraries.MessageEncodeConvert;

public class Message implements java.io.Serializable {

    private static final long serialVersionUID = -7654462121885214207L;
    
    private byte version;

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    private byte type;
    
    private int index;
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private MessageDecodeConvert messageDecodeConvert;
    
    private MessageEncodeConvert messageEncodeConvert;

    public byte getType() {
        return type;
    }
    
    public Message() {
    }
    
    public void setType(byte type) {
        this.type = type;
    }

    public MessageDecodeConvert getMessageDecodeConvert() {
        return messageDecodeConvert;
    }

    public void setMessageDecodeConvert(MessageDecodeConvert messageDecodeConvert) {
        this.messageDecodeConvert = messageDecodeConvert;
    }

    public MessageEncodeConvert getMessageEncodeConvert() {
        return messageEncodeConvert;
    }

    public void setMessageEncodeConvert(MessageEncodeConvert messageEncodeConvert) {
        this.messageEncodeConvert = messageEncodeConvert;
    }
}

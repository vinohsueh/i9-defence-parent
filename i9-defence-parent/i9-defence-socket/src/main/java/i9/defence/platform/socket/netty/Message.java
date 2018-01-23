package i9.defence.platform.socket.netty;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.message.MessageEncodeConvert;

public class Message implements java.io.Serializable {

    private static final long serialVersionUID = -7654462121885214207L;

    private byte type;

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

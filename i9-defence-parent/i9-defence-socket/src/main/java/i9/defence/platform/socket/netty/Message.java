package i9.defence.platform.socket.netty;

import i9.defence.platform.socket.message.MessageDecodeConvert;

public class Message implements java.io.Serializable {

    private static final long serialVersionUID = -7654462121885214207L;

    private byte type;

    private MessageDecodeConvert message;

    public byte getType() {
        return type;
    }

    public MessageDecodeConvert getMessage() {
        return message;
    }

    public Message(byte type, MessageDecodeConvert message) {
        this.type = type;
        this.message = message;
    }
}

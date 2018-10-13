package i9.defence.platform.datapush.utils;

public class ReceiveMessageDto {

    private Object msg;

    private String nonce;

    private String msgSignature;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getMsgSignature() {
        return msgSignature;
    }

    public void setMsgSignature(String msgSignature) {
        this.msgSignature = msgSignature;
    }

    @Override
    public String toString() {
        return "{ \"msg\":" + this.msg + "，\"nonce\":" + this.nonce + "，\"signature\":" + this.msgSignature + "}";
    }
}

package i9.defence.platform.datapush.utils;

/**
 * 接收消息数据传输对象
 * 
 * @author jiangtao
 */
public class ReceiveMessageDto {

    /**
     * 消息
     */
    private Object msg;

    /**
     * 随机数
     */
    private String nonce;

    /**
     * 消息签名
     */
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

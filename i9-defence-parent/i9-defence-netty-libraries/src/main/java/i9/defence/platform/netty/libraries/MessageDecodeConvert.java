package i9.defence.platform.netty.libraries;

import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.ByteBuf;


public abstract class MessageDecodeConvert {

    /**
     * 消息解码器
     * @param buf
     * @return
     */
    public abstract boolean decode(ByteBuf buf);

    /**
     * 消息包封装二进制数据组
     */
    public abstract byte[] getByteArray();
    
    /**
     * 将消息转换为json
     * @return
     */
    public abstract JSONObject toJSONObject();
}

package i9.defence.platform.netty.libraries;

import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.ByteBuf;


public abstract class MessageDecodeConvert {

    public abstract boolean decode(ByteBuf buf);

    public abstract byte[] getByteArray();
    
    public abstract JSONObject toJSONObject();
}

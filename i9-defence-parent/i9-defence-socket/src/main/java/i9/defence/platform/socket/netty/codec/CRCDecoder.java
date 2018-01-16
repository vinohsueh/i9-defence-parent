package i9.defence.platform.socket.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class CRCDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) throws Exception {
        // 处理通信解码
        byte[] dst = new byte[buf.readableBytes()];
        buf.readBytes(dst);
        // 生成通信字符串
        String str = new String(dst);
        list.add(str);
    }
}

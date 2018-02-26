package i9.defence.platform.socket.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<ByteBuf> {
    
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf buf) throws Exception {
        // 处理通信编码
        byte[] dst = new byte[msg.readableBytes()];
        msg.readBytes(dst);
        buf.writeBytes(dst);
    }
}
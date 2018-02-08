package i9.defence.platform.socket.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class MessageEncoder extends MessageToMessageEncoder<ByteBuf> {
    
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        byte[] dst = new byte[msg.readableBytes()];
        msg.readBytes(dst);
        out.add(dst);
    }
}

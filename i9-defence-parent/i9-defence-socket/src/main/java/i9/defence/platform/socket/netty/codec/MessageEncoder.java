package i9.defence.platform.socket.netty.codec;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import i9.defence.platform.socket.util.EncryptUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class MessageEncoder extends MessageToMessageEncoder<ByteBuf> {
    
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        byte[] dst = new byte[msg.readableBytes()];
        msg.readBytes(dst);
        out.add(dst);
        logger.info("发送数据, data : {}", EncryptUtils.bytesToHexString(dst));
    }
    
    private static final Logger logger = LoggerFactory.getLogger(MessageEncoder.class);
}

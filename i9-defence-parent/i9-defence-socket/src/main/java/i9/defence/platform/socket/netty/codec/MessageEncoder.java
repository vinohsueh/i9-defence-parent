package i9.defence.platform.socket.netty.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import i9.defence.platform.netty.libraries.EncryptUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<ByteBuf> {
    
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf buf) throws Exception {
        // 处理通信编码
        byte[] data = msg.array();
        buf.writeBytes(data);
        logger.info("发送数据, data : {}", EncryptUtils.bytesToHexString(data));
    }
    
    private static final Logger logger = LoggerFactory.getLogger(MessageEncoder.class);
}

package i9.defence.platform.socket.netty.codec;

import i9.defence.platform.netty.libraries.EncryptUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageEncoder extends MessageToByteEncoder<ByteBuf> {
    
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf buf) throws Exception {
        // 处理通信编码
        byte[] data = msg.array();
        buf.writeBytes(data);
        logger.error("发送数据, data : {}", EncryptUtils.bytesToHexString(data));
    }
    
    private static final Logger logger = LoggerFactory.getLogger("business");
}

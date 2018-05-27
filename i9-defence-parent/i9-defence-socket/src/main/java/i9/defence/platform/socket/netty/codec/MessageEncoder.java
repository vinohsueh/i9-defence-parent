package i9.defence.platform.socket.netty.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import i9.defence.platform.netty.libraries.EncryptUtils;
import i9.defence.platform.netty.libraries.MessageEncodeConvert;
import i9.defence.platform.netty.libraries.RespMessageBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<MessageEncodeConvert> {
    
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageEncodeConvert msg, ByteBuf buf) throws Exception {
        ByteBuf buf000 = RespMessageBuilder.wrapper(msg, 0);
    	// 处理通信编码
        byte[] data = buf000.array();
        buf.writeBytes(data);
        logger.info("发送数据, data : {}", EncryptUtils.bytesToHexString(data));
    }
    
    private static final Logger logger = LoggerFactory.getLogger(MessageEncoder.class);
}

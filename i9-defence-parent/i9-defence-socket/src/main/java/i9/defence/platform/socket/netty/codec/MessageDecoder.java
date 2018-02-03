package i9.defence.platform.socket.netty.codec;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.message.req.HeartbeatReqMessage;
import i9.defence.platform.socket.message.req.LoginReqMessage;
import i9.defence.platform.socket.message.req.UplinkReqMessage;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.util.EncryptUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    private static final Logger logger = LoggerFactory
            .getLogger(MessageDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) throws Exception {
        byte start = buf.readByte();
        byte version = buf.readByte();

        byte type = buf.readByte();
        int index = buf.readInt();

        int len = buf.readableBytes();

        len -= 2;
        byte[] dst = new byte[len];
        buf.readBytes(dst);

        byte sumcheck = buf.readByte();
        byte end = buf.readByte();

        logger.info(
                "解码, 起始符 : {}, 协议版本号 : {}, 消息类型 : {}, 消息索引 : {}, data : {}, 校验和 : {}, 结束符 : {}",
                String.format("%02X", start), String.format("%02X", version),
                String.format("%02X", type), index,
                EncryptUtils.bytesToHexString(dst),
                String.format("", sumcheck), String.format("%02X", end));

        MessageDecodeConvert messageDecodeConvert = null;
        if (type == 0x00) {
            messageDecodeConvert = new LoginReqMessage();
        } else if (type == 0xFF) {
            messageDecodeConvert = new HeartbeatReqMessage();
        } else {
            messageDecodeConvert = new UplinkReqMessage();
        }
        ByteBuf buf0 = Unpooled.buffer(len);
        buf0.writeBytes(dst);
        messageDecodeConvert.decode(buf0);

        Message message = new Message();
        message.setType(type);
        message.setMessageDecodeConvert(messageDecodeConvert);
        list.add(message);
    }
}

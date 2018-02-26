package i9.defence.platform.socket.netty.codec;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.message.req.HeartbeatReqMessage;
import i9.defence.platform.socket.message.req.LoginReqMessage;
import i9.defence.platform.socket.message.req.UplinkReqMessage;
import i9.defence.platform.socket.netty.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) throws Exception {
        buf.skipBytes(2);

        byte type = buf.readByte();
        buf.readByte();
        
        int len = buf.readableBytes();
        byte[] dst = new byte[len - 2];
        buf.readBytes(dst);
        
        buf.readByte();
        buf.readByte();
        
        MessageDecodeConvert messageDecodeConvert = null;
        if (type == 0x00) {
            messageDecodeConvert = new LoginReqMessage();
        }
        else if (type == 0xFF) {
            messageDecodeConvert = new HeartbeatReqMessage();
        }
        else {
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
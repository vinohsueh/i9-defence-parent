package org.gather.develop.service.netty.codec;

import org.apache.log4j.Logger;
import org.gather.develop.service.netty.util.CRC16;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class CRCEncoder extends MessageToByteEncoder<ByteBuf> {
    
    private static final Logger logger = Logger.getLogger(CRCEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf buf) throws Exception {
        // 处理通信编码
        byte[] dst = new byte[msg.readableBytes()];
        msg.readBytes(dst);
        
        String str = new String(dst) + ",";

        //生成CRC编码
        String crc = CRC16.getCRC16Hex(str);
        String hex;
        {
            StringBuffer stringBuffer = new StringBuffer();
            // 拼取发送设备信息
            stringBuffer.append("$,").append(str).append(crc).append("*");
            String data = stringBuffer.toString();
            int len = data.length();
            hex = Integer.toHexString(len);
            len += hex.length() + 1;
            hex = Integer.toHexString(len);
        }
        StringBuffer stringBuffer = new StringBuffer();
        // 拼取发送设备信息
        stringBuffer.append("$,").append(hex).append(",").append(str).append(crc).append("*");
        
        buf.writeBytes(stringBuffer.toString().getBytes());

        logger.info("设备 : " + ctx.channel().id().asLongText() + ", 发送数据 : " + stringBuffer.toString());
    }
}

package org.gather.develop.service.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.gather.develop.service.netty.util.CRC16;
import org.gather.develop.service.util.StringUtil;

public class CRCDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) throws Exception {
        // 处理通信解码
        byte[] dst = new byte[buf.readableBytes()];
        buf.readBytes(dst);
        
        // 生成通信字符串
        String str = new String(dst);
        
        //判断字符是否有效
        str = checkDataValid(str);
        
        // 截取掉数据长度
        str = str.substring(str.indexOf(",") + 1);
        str = str.substring(str.indexOf(",") + 1);
        
        // 截取CRC十六进制
        String crc = str.substring(str.lastIndexOf(",") + 1);
        
        // 截取数据区
        String s = str.substring(0, str.lastIndexOf(",") + 1);
        
        // 校验CRC数据
        if (!crc.equals(CRC16.getCRC16Hex(s))) {
            return;
        }

        list.add(s);
    }

    private String checkDataValid(String str) {
        // 判断通信格式
        if (str.charAt(0) != '$') {
            return StringUtil.EMPTY;
        }
        if (str.charAt(str.length() - 1) != '*') {
            return StringUtil.EMPTY;
        }
        return str.substring(1, str.length() - 1);
    }
    
    public static void main(String[] args) {
		System.out.println(CRC16.getCRC16Hex("BCT,170117153908,12345678,000012345678,2017-01-17#11:05:54,00,00,0001,0001,0001,4178,,,00000000,"));
	}
}

package i9.defence.platform.socket.test;

import i9.defence.platform.netty.libraries.EncryptUtils;
import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.netty.codec.MessageDecoder;
import i9.defence.platform.socket.service.impl.UpStreamService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:beans.xml"}) 
public class UpStreamReqMessageTest {

    @Test
    public void testMessageDecoder() throws Exception {
        byte[] data = EncryptUtils.hexStringToBytes("4011030000000330040000000000070300000000EC2C0240000000FFFFFFFFFFFF0400000001010000FFFFFFFFFFFF0400000001020000FFFFFFFFFFFF0400000001030000FFFFFFFFFFFF0400000001040000FFFFFFFFFFFF0400000001050000FFFFFFFFFFFF0400000001060000FFFFFFFFFFFF0400000001070000FFFFFFFFFFFF0400000001080000FFFFFFFFFFFF0400000001090000FFFFFFFFFFFF04000000010A0000FFFFFFFFFFFF04000000010B0000FFFFFFFFFFFF04000000010C0000FFFFFFFFFFFF04000000010D0000FFFFFFFFFFFF04000000010E0000FFFFFFFFFFFF04000000010F0000FFFFFFFFFFFF0400000001100000FFFFFFFFFFFF0400000001110000FFFFFFFFFFFF0400000001120002FFFFFFFFFFFF020898130002FFFFFFFFFFFF020898140002FFFFFFFFFFFF020898150002FFFFFFFFFFFF020898160002FFFFFFFFFFFF020898170002FFFFFFFFFFFF020898180002FFFFFFFFFFFF020898190002FFFFFFFFFFFF0208981A0002FFFFFFFFFFFF0208981B0002FFFFFFFFFFFF0208981C0002FFFFFFFFFFFF0208981D0002FFFFFFFFFFFF0208981E0002FFFFFFFFFFFF0208981F0002FFFFFFFFFFFF020898200005FFFFFFFFFFFF0400000898210005FFFFFFFFFFFF0400000898220005FFFFFFFFFFFF0400000898230002FFFFFFFFFFFF020898240002FFFFFFFFFFFF020898250002FFFFFFFFFFFF020898260002FFFFFFFFFFFF020898270002FFFFFFFFFFFF020898280002FFFFFFFFFFFF020898290004FFFFFFFFFFFF04000008982A0004FFFFFFFFFFFF04000008982B0004FFFFFFFFFFFF04000008987623".trim());
       ByteBuf buf = Unpooled.buffer(data.length);
        buf.writeBytes(data);
        
        MessageDecoder messageDecoder = new MessageDecoder();
        List<Object> list = new ArrayList<Object>();
        messageDecoder.decode(null, buf, list);
        Message message = (Message) list.get(0);
        
        ChannelPacker channelPacker = new ChannelPacker();
        upStreamService.doPost(message, channelPacker);
    }
    
    @Autowired
    private UpStreamService upStreamService;
}

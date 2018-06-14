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
        byte[] data = EncryptUtils.hexStringToBytes("401003000000030003000000000002030000000002010e000000ffffffffffff04000000002D23".trim());
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

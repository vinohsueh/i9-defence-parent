package i9.defence.platform.socket.test;

import i9.defence.platform.netty.libraries.EncryptUtils;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.netty.codec.MessageDecoder;
import i9.defence.platform.socket.service.impl.UpStreamService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

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
    public void testMessageDecoder() {
        byte[] data = EncryptUtils.hexStringToBytes("40100300000263000200000000000103001FFFFFFE1000C8000003FFFFFFFFFFFF0000010003FFFFFFFFFFFF0000020003FFFFFFFFFFFF0000030003FFFFFFFFFFFF0000040005FFFFFFFFFFFF00000000050005FFFFFFFFFFFF00000000060005FFFFFFFFFFFF00000000070005FFFFFFFFFFFF00000000080000FFFFFFFFFFFF00000000090000FFFFFFFFFFFF000000000A0000FFFFFFFFFFFF000000000B0000FFFFFFFFFFFF000000000C0000FFFFFFFFFFFF000000000D0000FFFFFFFFFFFF85A185470E0000FFFFFFFFFFFF859085A60F0000FFFFFFFFFFFF8591853AF023");
        MessageDecoder messageDecoder = new MessageDecoder();
        ChannelHandlerContext ctx = null;
        ByteBuf buf = Unpooled.buffer(data.length);
        buf.writeBytes(data);
        List<Object> list = new ArrayList<Object>();
        try {
            messageDecoder.decode(ctx, buf, list);
            Message message = (Message) list.get(0);
            upStreamService.doPost(message, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    @Autowired
//    private HeartbeatService heartbeatService;
    
    @Autowired
    private UpStreamService upStreamService;
}

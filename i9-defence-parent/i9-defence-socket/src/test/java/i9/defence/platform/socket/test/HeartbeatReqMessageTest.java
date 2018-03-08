package i9.defence.platform.socket.test;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.netty.codec.MessageDecoder;
import i9.defence.platform.socket.service.impl.HeartbeatService;
import i9.defence.platform.socket.util.EncryptUtils;
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
public class HeartbeatReqMessageTest {

    @Test
    public void testMessageDecoder() {
        byte[] data = EncryptUtils.hexStringToBytes("4010ff00000001045aa002b7c723");
        MessageDecoder messageDecoder = new MessageDecoder();
        ChannelHandlerContext ctx = null;
        ByteBuf buf = Unpooled.buffer(data.length);
        buf.writeBytes(data);
        List<Object> list = new ArrayList<Object>();
        try {
            messageDecoder.decode(ctx, buf, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Message message = (Message) list.get(0);
        ChannelPacker channelPacker = new ChannelPacker();
        heartbeatService.doPost(message, channelPacker);
    }
    
    @Autowired
    private HeartbeatService heartbeatService;
}

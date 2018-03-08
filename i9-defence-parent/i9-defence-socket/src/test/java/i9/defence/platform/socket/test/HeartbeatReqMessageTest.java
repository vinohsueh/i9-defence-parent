package i9.defence.platform.socket.test;

import i9.defence.platform.socket.context.ChannelPacker;
import i9.defence.platform.socket.message.req.UpStreamReqMessage;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.service.impl.UpStreamService;
import i9.defence.platform.socket.util.EncryptUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

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
        //解码, 起始符 : 40, 协议版本号 : 10, 消息类型 : 03, 消息索引 : 51, data : 00020000000000080300000000550A0078000009FFFFFFFFFFFF02000A010009FFFFFFFFFFFF020014020009FFFFFFFFFFFF02001E030009FFFFFFFFFFFF020028040009FFFFFFFFFFFF02000A050009FFFFFFFFFFFF020014060009FFFFFFFFFFFF02001E070009FFFFFFFFFFFF020028080009FFFFFFFFFFFF02001E090009FFFFFFFFFFFF020028, 校验和 : 97, 结束符 : 23
        byte[] data = EncryptUtils.hexStringToBytes("00020000000000080300000000550A0078000009FFFFFFFFFFFF02000A010009FFFFFFFFFFFF020014020009FFFFFFFFFFFF02001E030009FFFFFFFFFFFF020028040009FFFFFFFFFFFF02000A050009FFFFFFFFFFFF020014060009FFFFFFFFFFFF02001E070009FFFFFFFFFFFF020028080009FFFFFFFFFFFF02001E090009FFFFFFFFFFFF020028");
//        MessageDecoder messageDecoder = new MessageDecoder();
//        ChannelHandlerContext ctx = null;
        ByteBuf buf = Unpooled.buffer(data.length);
        buf.writeBytes(data);
//        List<Object> list = new ArrayList<Object>();
//        try {
//            messageDecoder.decode(ctx, buf, list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Message message = (Message) list.get(0);
        ChannelPacker channelPacker = new ChannelPacker();
        UpStreamReqMessage upStreamReqMessage = new UpStreamReqMessage();
        upStreamReqMessage.decode(buf);
        Message message = new Message();
        message.setMessageDecodeConvert(upStreamReqMessage);
        upStreamService.doPost(message, channelPacker);
    }
    
//    @Autowired
//    private HeartbeatService heartbeatService;
    
    @Autowired
    private UpStreamService upStreamService;
}

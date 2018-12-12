package i9.defence.platform.socket.test;

import i9.defence.platform.netty.libraries.EncryptUtils;
import i9.defence.platform.netty.libraries.req.LoginReqMessage;
import i9.defence.platform.netty.libraries.req.UpStreamReqMessage;
import i9.defence.platform.socket.netty.Message;
import i9.defence.platform.socket.netty.codec.MessageDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:beans.xml" })
public class LoginReqMessageTest {

    @Test
    public void testMessageDecoder() {
        System.out.println(Integer.toHexString(2));

        byte[] a = { 0, 0, 0, 2 };
        System.out.println(EncryptUtils.bytesToHexString(a));

        // 解码, 起始符 : 40, 协议版本号 : 10, 消息类型 : 03, 消息索引 : 51, data :
        // 00020000000000080300000000550A0078000009FFFFFFFFFFFF02000A010009FFFFFFFFFFFF020014020009FFFFFFFFFFFF02001E030009FFFFFFFFFFFF020028040009FFFFFFFFFFFF02000A050009FFFFFFFFFFFF020014060009FFFFFFFFFFFF02001E070009FFFFFFFFFFFF020028080009FFFFFFFFFFFF02001E090009FFFFFFFFFFFF020028,
        // 校验和 : 97, 结束符 : 23
        String hex = "40 11 03 00 00 00 01 0B BC 00 00 00 00 01 00 03 00 00 00 00 02 29 02 16 00 00 00 FF FF FF FF FF FF 04 00 00 00 06 01 00 00 FF FF FF FF FF FF 04 00 00 00 01 02 00 00 FF FF FF FF FF FF 04 00 00 00 02 03 00 00 FF FF FF FF FF FF 04 00 00 00 00 04 00 00 FF FF FF FF FF FF 04 00 00 00 03 05 00 00 FF FF FF FF FF FF 04 00 00 00 00 06 00 00 FF FF FF FF FF FF 04 00 00 00 04 07 00 00 FF FF FF FF FF FF 04 00 00 00 05 08 00 00 FF FF FF FF FF FF 04 00 00 00 06 09 00 00 FF FF FF FF FF FF 04 00 00 00 07 0A 00 00 FF FF FF FF FF FF 04 00 00 00 08 0B 00 00 FF FF FF FF FF FF 04 00 00 00 09 0C 00 00 FF FF FF FF FF FF 04 00 00 00 0A 0D 00 00 FF FF FF FF FF FF 04 00 00 00 0B 0E 00 00 FF FF FF FF FF FF 04 00 00 00 0C 0F 00 02 FF FF FF FF FF FF 02 00 0B 10 00 02 FF FF FF FF FF FF 02 00 0D 11 00 02 FF FF FF FF FF FF 02 00 0E 12 00 02 FF FF FF FF FF FF 02 00 0F 13 00 02 FF FF FF FF FF FF 02 03 E8 14 00 02 FF FF FF FF FF FF 02 03 F2 15 00 02 FF FF FF FF FF FF 02 03 FC 16 00 02 FF FF FF FF FF FF 02 04 06 17 00 02 FF FF FF FF FF FF 02 08 98 18 00 02 FF FF FF FF FF FF 02 08 B6 19 00 02 FF FF FF FF FF FF 02 07 60 1A 00 02 FF FF FF FF FF FF 02 03 32 1B 00 02 FF FF FF FF FF FF 02 02 68 1C 00 02 FF FF FF FF FF FF 02 02 02 1D 00 03 FF FF FF FF FF FF 04 00 00 01 4D 1E 00 03 FF FF FF FF FF FF 04 00 00 01 4E 1F 00 03 FF FF FF FF FF FF 04 00 00 01 4F 20 00 02 FF FF FF FF FF FF 02 83 E8 21 00 02 FF FF FF FF FF FF 02 03 E8 22 00 02 FF FF FF FF FF FF 02 83 E8 23 00 02 FF FF FF FF FF FF 02 01 F3 24 00 02 FF FF FF FF FF FF 02 02 57 25 00 02 FF FF FF FF FF FF 02 01 8F 26 00 04 FF FF FF FF FF FF 04 00 00 11 AC 27 00 04 FF FF FF FF FF FF 04 00 00 1A 0A 28 00 04 FF FF FF FF FF FF 04 00 01 5B 38 F4 23"
                .replaceAll(" ", "");
        byte[] data = EncryptUtils.hexStringToBytes(hex);
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
        UpStreamReqMessage upStreamReqMessage = (UpStreamReqMessage) message.getMessageDecodeConvert();
        JSONObject jsonObject = upStreamReqMessage.toJSONObject();
        System.err.println(jsonObject.toJSONString());
        // upStreamReqMessage.decode(buf);
        // Message message = new Message();
        // message.setMessageDecodeConvert(upStreamReqMessage);
        // upStreamService.doPost(message, channelPacker);
    }

    // @Autowired
    // private HeartbeatService heartbeatService;

    // @Autowired
    // private UpStreamService upStreamService;
}

package i9.defence.platform.socket.test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import i9.defence.platform.netty.libraries.req.DataMessage;
import i9.defence.platform.netty.libraries.req.UpStreamReqMessage;
import i9.defence.platform.utils.EncryptUtils;

public class Test {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] { (byte) 254, (byte) 255, 0, 1 });
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        for (int i = 1; i <= 4; i++) {
            int a = dataInputStream.readUnsignedByte();
            System.out.println(a);
        }

        byte a = (byte) 0xff;
        System.out.println(EncryptUtils.byteToPositive(a));

        a = (byte) 254;
        System.out.println(EncryptUtils.byteToPositive(a));
        
        DataMessage dataMessage = new DataMessage();
        dataMessage.channelId = (byte) 0xfe;
        
        UpStreamReqMessage upStreamReqMessage = new UpStreamReqMessage();
        upStreamReqMessage.dataList.add(dataMessage);
        
        System.out.println(upStreamReqMessage.isHeartbeatDataMessage());
        System.out.println(upStreamReqMessage.isLoginDataMessage());
    }
}

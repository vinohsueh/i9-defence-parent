package i9.defence.platform.client;

import i9.defence.platform.netty.libraries.EncryptUtils;
import i9.defence.platform.netty.libraries.req.UpStreamReqMessage;

import java.nio.ByteBuffer;

public class ClientRun {

    public static void main(String[] args) {
        Client client = Client.getInstance();
        Message message = new Message();
        while (true) {
//            UpStreamReqMessage upStreamReqMessage = message.makeUpStreamReqMessage();
//            byte[] data = upStreamReqMessage.getByteArray();
//            int len = data.length + 1 + 1 + 1 + 4 + 1 + 1;
//            ByteBuffer byteBuffer2 = ByteBuffer.allocate(len);
//            byteBuffer2.put((byte) 0x40);
//            byteBuffer2.put((byte) 0x10);
//            byteBuffer2.put((byte) 0x01);
//            byteBuffer2.putInt(1);
//            byteBuffer2.put(data);
//            byteBuffer2.put((byte) 0x00);
//            byteBuffer2.put((byte) 0x23);
//            client.sendMessage(byteBuffer2.array());
//            byte [] data = EncryptUtils.hexStringToBytes("401000000002610B000000000001001FFFFFFEBF23");
//            byte [] data = EncryptUtils.hexStringToBytes("4010FF000002620B000000000001001FFFFFFEBF23");
            byte [] data = EncryptUtils.hexStringToBytes("40100300000263000200000000000103001FFFFFFE1000C8000003FFFFFFFFFFFF0000010003FFFFFFFFFFFF0000020003FFFFFFFFFFFF0000030003FFFFFFFFFFFF0000040005FFFFFFFFFFFF00000000050005FFFFFFFFFFFF00000000060005FFFFFFFFFFFF00000000070005FFFFFFFFFFFF00000000080000FFFFFFFFFFFF00000000090000FFFFFFFFFFFF000000000A0000FFFFFFFFFFFF000000000B0000FFFFFFFFFFFF000000000C0000FFFFFFFFFFFF000000000D0000FFFFFFFFFFFF85A185470E0000FFFFFFFFFFFF859085A60F0000FFFFFFFFFFFF8591853AF023");
            client.sendMessage(data);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

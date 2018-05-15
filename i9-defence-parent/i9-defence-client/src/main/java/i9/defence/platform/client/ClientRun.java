package i9.defence.platform.client;

import i9.defence.platform.netty.libraries.req.LoginReqMessage;

import java.nio.ByteBuffer;

public class ClientRun {

    public static void main(String[] args) {
        Client client = Client.getInstance();
        Message message = new Message();
        while (true) {
//            UpStreamReqMessage upStreamReqMessage = message.makeUpStreamReqMessage();
//            byte[] data = upStreamReqMessage.getByteArray();
            LoginReqMessage loginReqMessage = message.makeLoginReqMessage();
            byte[] data = loginReqMessage.getByteArray();
            int len = data.length + 1 + 1 + 1 + 4 + 1 + 1;
            ByteBuffer byteBuffer2 = ByteBuffer.allocate(len);
            byteBuffer2.put((byte) 0x40);
            byteBuffer2.put((byte) 0x10);
            byteBuffer2.put((byte) 0x00);
            byteBuffer2.putInt(1);
            byteBuffer2.put(data);
            byteBuffer2.put((byte) 0x00);
            byteBuffer2.put((byte) 0x23);
            client.sendMessage(byteBuffer2.array());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

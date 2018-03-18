package i9.defence.platform.client;

import java.nio.ByteBuffer;

import i9.defence.platform.netty.libraries.req.UpStreamReqMessage;

public class ClientRun {

    public static void main(String[] args) {
        Client client = Client.getInstance();
        Message message = new Message();
        while (true) {
            UpStreamReqMessage upStreamReqMessage = message.makeUpStreamReqMessage();
            byte[] data = upStreamReqMessage.getByteArray();
            int len = data.length + 1 + 1 + 1 + 4 + 1 + 1;
            ByteBuffer byteBuffer2 = ByteBuffer.allocate(len);
            byteBuffer2.put((byte) 0x40);
            byteBuffer2.put((byte) 0x10);
            byteBuffer2.put((byte) 0x03);
            byteBuffer2.putInt(1);
            byteBuffer2.put(data);
            byteBuffer2.put((byte) 0x00);
            byteBuffer2.put((byte) 0x23);
            client.sendMessage(data);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

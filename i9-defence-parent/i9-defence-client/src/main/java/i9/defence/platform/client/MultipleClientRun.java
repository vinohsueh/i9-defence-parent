package i9.defence.platform.client;

import java.nio.ByteBuffer;

public class MultipleClientRun {

    public static void main(String[] args) {
        while (true) {
            Client client = Client.newInstance();
            int index = 0;
            ByteBuffer byteBuffer2 = client.randomMessageByteBuffer(index);
            client.sendMessage(byteBuffer2.array());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

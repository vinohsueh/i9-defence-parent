package i9.defence.platform.client;

import java.nio.ByteBuffer;

public class ClientRun {

    public static void main(String[] args) {
        Client client = Client.getInstance();
        int index = 0;
        while (true) {
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

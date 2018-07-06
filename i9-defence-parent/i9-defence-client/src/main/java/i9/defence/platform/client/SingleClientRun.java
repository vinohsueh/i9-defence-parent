package i9.defence.platform.client;

import java.nio.ByteBuffer;

public class SingleClientRun {

    public static void main(String[] args) {
        Client.SERVERIP = "103.248.102.21";
        Client client = Client.getInstance();
        while (true) {
            int index = 2;
            ByteBuffer byteBuffer2 = client.randomMessageByteBuffer(index);
//            byte[] data = EncryptUtils.hexStringToBytes("4010FF0000000B0B000000000001001000FFD50A23");
            client.sendMessage(byteBuffer2.array());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

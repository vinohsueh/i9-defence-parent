package i9.defence.platform.client;

import i9.defence.platform.netty.libraries.req.UpStreamReqMessage;

public class ClientRun {

    public static void main(String[] args) {
        Client client = Client.getInstance();
        while (true) {
            UpStreamReqMessage upStreamReqMessage = new UpStreamReqMessage();
            byte[] data = upStreamReqMessage.getByteArray();
            client.sendMessage(data);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

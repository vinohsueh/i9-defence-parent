package i9.defence.platform.client;

import i9.defence.platform.netty.libraries.req.LoginReqMessage;
import i9.defence.platform.netty.libraries.req.UpStreamReqMessage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Client {

    public static String SERVERIP = "127.0.0.1";

    public static int SERVERPORT = 9000;

    public void connect(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            System.out.println("连接服务器成功, ip : " + ip + ", port : " + port);
        } catch (Exception e) {
            System.out.println("连接服务器失败, ip : " + ip + ", port : " + port);
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            this.socket.close();
            this.socket = null;
            System.out.println("连接服务器断开成功");
        } catch (IOException e) {
            System.out.println("连接服务器断开失败");
            e.printStackTrace();
        }
    }

    public Client() {
        this.connect(SERVERIP, SERVERPORT);
    }

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public static Client newInstance() {
        return new Client();
    }

    private static Client instance;

    private Socket socket;

    public void sendMessage(byte[] data) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
            System.out.println("客户端发送消息成功");
        } catch (IOException e) {
            System.out.println("客户端发送消息失败");
            e.printStackTrace();
        }
    }

    public ByteBuffer randomMessageByteBuffer(int index) {
        Message message = new Message();
        byte[] data;
        byte m;
        if (index != 0) {
            UpStreamReqMessage upStreamReqMessage = message.makeUpStreamReqMessage();
            data = upStreamReqMessage.getByteArray();
            m = 0x01;
        } else {
            LoginReqMessage loginReqMessage = message.makeLoginReqMessage();
            data = loginReqMessage.getByteArray();
            m = 0x00;
        }
        int len = data.length + 1 + 1 + 1 + 4 + 1 + 1;
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(len);
        byteBuffer2.put((byte) 0x40);
        byteBuffer2.put((byte) 0x10);
        byteBuffer2.put(m);
        byteBuffer2.putInt(1);
        byteBuffer2.put(data);
        byteBuffer2.put((byte) 0x00);
        byteBuffer2.put((byte) 0x23);
        return byteBuffer2;
    }
}

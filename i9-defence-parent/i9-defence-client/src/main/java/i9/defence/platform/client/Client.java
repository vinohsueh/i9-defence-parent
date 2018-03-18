package i9.defence.platform.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

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
        this.connect("127.0.0.1", 9000);
    }

    public static Client getInstance() {
        return instance;
    }

    private static final Client instance = new Client();

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
}

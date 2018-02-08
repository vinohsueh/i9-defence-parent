package i9.defence.platform.socket.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import i9.defence.platform.socket.util.EncryptUtils;

public class MessageTest {

    public static void main(String[] args) throws UnknownHostException, IOException {
        String hex = "BAF204C78E089916FFAEFF04C5FD092F";
        byte[] data = EncryptUtils.hexStringToBytes(hex);
        
        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length + 10);
        byteBuffer.put((byte) 0x40);
        byteBuffer.put((byte) 0x10);
        byteBuffer.put((byte) 0x00);
        byteBuffer.putInt(1);

        byteBuffer.put((byte) data.length);
        byteBuffer.put(data);
        
        byteBuffer.put((byte) 0x00);
        byteBuffer.put((byte) 0x23);
        
        System.out.println("send" + byteBuffer.array().length);
        
        @SuppressWarnings("resource")
        Socket socket = new Socket("127.0.0.1", 9000);
        while (true) {
            try {
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(byteBuffer.array());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

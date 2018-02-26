package i9.defence.platform.socket.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class MessageTest {

    public static void main(String[] args) throws UnknownHostException, IOException {

        byte[] data = new byte[] { 1, 2, 3, 4 };
        ByteBuffer byteBuffer = ByteBuffer.allocate(1 + 2 + 6 + 1 + data.length);
        byteBuffer.put((byte) 100);
        byteBuffer.putShort((short) 99);
        byteBuffer.put((byte) 18);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 8);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) data.length);
        byteBuffer.put(data);
        byte[] data0 = byteBuffer.array();
        
        int len = data0.length * 10 + 1 + 1 + 1 + 4 + 2 + 6 + 1 + 1 + 4 + 1 + 2 + 1 + 1;
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(len);
        byteBuffer2.put((byte) 0x40);
        byteBuffer2.put((byte) 0x10);
        byteBuffer2.put((byte) 0x01);
        byteBuffer2.putInt(1);
        byteBuffer2.putShort((short) 1);
        byte[] data1 = new byte[] { 1, 2, 3, 4, 5, 6 };
        byteBuffer2.put(data1);
        byteBuffer2.put((byte) 1);
        byteBuffer2.put((byte) 2);
        byteBuffer2.putInt(100);
        
        byteBuffer2.put((byte) 10);
        byteBuffer2.putShort((short) ((short) data0.length * 10));
        
        for (int i = 0; i < 10; i ++) {
            byteBuffer2.put(data0);
        }
        
        byteBuffer2.put((byte) 0x00);
        byteBuffer2.put((byte) 0x23);
        
        byte[] data2 = byteBuffer2.array();
        
        System.out.println(data2.length + " " + len);

        @SuppressWarnings("resource")
        Socket socket = new Socket("127.0.0.1", 9000);
        while (true) {
            try {
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(data2);
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

package i9.defence.platform.socket.test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] {(byte) 2222, 0 , 0, 1});
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        for (int i = 1; i <= 4; i ++) {
            int a = dataInputStream.readUnsignedByte();
            System.out.println(a);
        }
    }
}

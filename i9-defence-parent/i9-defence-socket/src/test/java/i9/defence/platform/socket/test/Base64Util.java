package i9.defence.platform.socket.test;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Base64Util {

    // base64字符串转byte[]
    public static byte[] base64String2ByteFun(String base64Str) {
        return Base64.decodeBase64(base64Str);
    }

    // byte[]转base64
    public static String byte2Base64StringFun(byte[] b) {
        return Base64.encodeBase64String(b);
    }
    
    @Test
    public void testBase64() {
        byte[] bs = new byte[] {0x00, 0x01, 0x02, 0x03};
        String base64Str = byte2Base64StringFun(bs);
        System.out.println(base64Str);
        bs = Base64Util.base64String2ByteFun(base64Str);
        System.out.println(bs);
    }
}

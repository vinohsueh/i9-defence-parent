package i9.defence.platform.socket.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtils {

    public static byte[] SumCheck(byte[] buf) {
        int length = 8;
        long sum = 0;
        byte[] mByte = new byte[length];
        for (byte b : buf) {
            long num = ((long) b >= 0) ? (long) b : ((long) b + 256);
            sum += num;
        }
        for (int count = 0; count < length; count++) {
            mByte[length - count - 1] = (byte) (sum >> (count * 8) & 0xff);
        }
        return mByte;
    }

    public static String bytesToHexString(byte[] buf) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (buf == null || buf.length <= 0) {
            return null;
        }
        for (int i = 0; i < buf.length; i++) {
            int v = buf[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    
    public static void main(String[] args) {
        byte[] data = new byte[] { 0, 0, 0, 0 };
        byte[] key = new byte[] { 0, 0, 0 };
        byte[] dst = Aes256Encode(data, key);
        
    }

    public static byte[] Aes256Encode(byte[] data, byte[] key) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); // 生成加密解密需要的Key
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            result = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String Aes256Decode(byte[] data, byte[] key) {
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); // 生成加密解密需要的Key
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decoded = cipher.doFinal(data);
            result = new String(decoded, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

package i9.defence.platform.utils;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncryptUtils {

    /**
     * XOR 异或算法
     * @param buf
     * @param length
     * @return
     */
    public static byte XOR(byte[] buf, int length) {
        byte j, value = 0;
        for (j = 0; j < length; j++) {
            value ^= buf[j];
        }
        return value;
    }
    
    public static void main(String[] args) {
		System.out.println(bytesToHexString(intToBytes(1)));
	}
    
    /**
     * 字节数组转INT
     * @param buffer
     * @return
     */
    public static int bytesToInt(byte[] buffer) {
        return  buffer[3] & 0xFF |
                (buffer[2] & 0xFF) << 8 |
                (buffer[1] & 0xFF) << 16 |
                (buffer[0] & 0xFF) << 24;
    }
    
    /**
     * INT转字节数组
     * @param value
     * @return
     */
    public static byte[] intToBytes(int value) {
        return new byte[] {
            (byte) ((value >> 24) & 0xFF),
            (byte) ((value >> 16) & 0xFF),
            (byte) ((value >> 8) & 0xFF),
            (byte) (value & 0xFF)
        };
    }
    
    /**
     * LONG转字节数组
     * @param values
     * @return
     */
    public static byte[] longToBytes(long values) {
        byte[] buffer = new byte[8];
        for (int i = 0; i < 8; i++) {
            int offset = 64 - (i + 1) * 8;
            buffer[i] = (byte) ((values >> offset) & 0xff);
        }
        return buffer;
    }

    /**
     * 字节数组转LONG
     * @param buffer
     * @return
     */
    public static long bytesToLong(byte[] buffer) {
        long values = 0;
        for (int i = 0; i < 8; i++) {
            values <<= 8;
            values |= (buffer[i] & 0xff);
        }
        return values;
    }
    
    /**
     * 日期转字节数组(7个字节)
     * @param calendar
     * @return
     */
    public static byte[] calendarToBytes7(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        String yearString = String.valueOf(year);
        int yearFir = Integer.parseInt(yearString.substring(0,2));
        int yearLast = Integer.parseInt(yearString.substring(2));
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        ByteBuffer byteBuffer = ByteBuffer.allocate(7);
        byteBuffer.put(new byte[] {(byte) yearFir,(byte) yearLast,(byte) month,(byte) day,(byte) hour, (byte) minute,0x00});
        return byteBuffer.array();
    }
    /**
     * 字节数组转日期(7个字节)
     * @param bytes
     * @return
     */
    public static Calendar bytesToCalendar7(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        int yearFir = byteBuffer.get();
        int yearLast = byteBuffer.get();
        int month = byteBuffer.get();
        int day = byteBuffer.get();
        int hour = byteBuffer.get();
        int minute = byteBuffer.get();
        int year = Integer.valueOf(String.valueOf(yearFir)+String.valueOf(yearLast));
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar;
    }
    
    /**
     * 日期转字节数组(4位)
     * @param calendar
     * @return
     */
    public static byte[] calendarToBytes4(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        String yearString = String.valueOf(year);
        int yearFir = Integer.parseInt(yearString.substring(0,2));
        int yearLast = Integer.parseInt(yearString.substring(2));
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        ByteBuffer byteBuffer = ByteBuffer.allocate(4   );
        byteBuffer.put(new byte[] {(byte) yearFir,(byte) yearLast,(byte) month,(byte) day});
        return byteBuffer.array();
    }
    
    
    /**
     * 字节数组转日期(4位)
     * @param bytes
     * @return
     */
    public static Calendar bytesToCalendar4(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        int yearFir = byteBuffer.get();
        int yearLast = byteBuffer.get();
        int month = byteBuffer.get();
        int day = byteBuffer.get();
        int year = Integer.valueOf(String.valueOf(yearFir)+String.valueOf(yearLast));
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar;
    }
    
    /**
     * 日期转字节数组(4位)
     * @param calendar
     * @return
     */
    public static byte[] calendarToBytes6(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        String yearString = String.valueOf(year);
        int yearLast = Integer.parseInt(yearString.substring(2));
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        ByteBuffer byteBuffer = ByteBuffer.allocate(6);
        byteBuffer.put(new byte[] {(byte) yearLast,(byte) month,(byte) day,(byte) hour, (byte) minute,0x00});
        return byteBuffer.array();
    }
    
    /**
     * 字节数组转日期(4位)
     * @param bytes
     * @return
     */
    public static Calendar bytesToCalendar6(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        int yearLast = byteBuffer.get();
        int month = byteBuffer.get();
        int day = byteBuffer.get();
        int hour = byteBuffer.get();
        int minute = byteBuffer.get();
        int year = Integer.valueOf("20"+String.valueOf(yearLast));
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar;
    }
    /**
     * DES加密算法
     * @param dataBytes
     * @param keyBytes
     * @return
     */
    public static byte[] encrypt(byte[] dataBytes, byte[] keyBytes) {
        try {
         // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(keyBytes);
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(dataBytes);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES解密算法
     * @param dataBytes
     * @param keyBytes
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] dataBytes, byte[] keyBytes) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(keyBytes);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(dataBytes);
    }

    /**
     * 字节数组转十六进制字符串
     * @param buf
     * @return
     */
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

    /**
     * 十六进制字符串转字节数组
     * @param hexString
     * @return
     */
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
    
    /**
     * 数字字符串转字节数组
     * @param number
     * @return
     */
    public static byte[] numberStringToByte(String number) {
        byte[] bytes=number.getBytes();
        for(int i=bytes.length-1;i>=0;i--){
            bytes[i]-=(byte)'0';
        }
        return bytes;
    }
    
    /**
     * 字节数组转数字字符串
     * @param number
     * @return
     */
    public static String byteToNumberString(byte[] bytes) {
        for(int i=bytes.length-1;i>=0;i--){
            bytes[i]+=(byte)'0';
        }
        return bytes.toString();
    }
}

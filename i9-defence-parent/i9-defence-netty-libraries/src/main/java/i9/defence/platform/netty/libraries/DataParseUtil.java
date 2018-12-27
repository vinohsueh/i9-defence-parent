package i9.defence.platform.netty.libraries;

import java.io.IOException;
import java.math.BigDecimal;

public class DataParseUtil {

    public static void main(String[] args) throws IOException {
        {
            byte[] b = EncryptUtils.hexStringToBytes("019E");
            System.out.println(parseDataValue((byte) 2, b));
        }
        {
            byte[] b = EncryptUtils.hexStringToBytes("00000005");
            System.out.println(parseDataValue((byte) 0, b));
        }
//        {
//            byte[] b = EncryptUtils.hexStringToBytes("00000112");
//            System.out.println(parseDataValue((byte) 3, b));
//        }
        {
            byte[] b = EncryptUtils.hexStringToBytes("00000112");
            System.out.println(parseDataValue((byte) 5, b));
        }
        {
            byte[] b = EncryptUtils.hexStringToBytes("00000010");
            System.out.println(parseDataValue((byte) 3, b));
        }
    }

    public static Object parseDataValue(byte type, byte[] b) {
        DataEnum dataEnum = DataEnum.valueOf00(type);
        if (dataEnum != DataEnum.T_ENUM && dataEnum != DataEnum.T_ENUM_0 && dataEnum != DataEnum.T_PACKET
                && dataEnum != DataEnum.T_STRING) {
            b = reverse(b);
        }
        switch (dataEnum) {
        case T_ENUM:
            return DataParseUtil.parseEnum(b);

        case T_UNSIGNED_CHAR:
            return DataParseUtil.parseUnsignedChar(b);

        case T_UNSIGNED_SHORT:
            return DataParseUtil.parseUnsignedShort(b);

        case T_UNSIGNED_INT:
            return DataParseUtil.parseUnsignedInt(b);

        case T_SIGNED_LONG:
            return DataParseUtil.parseSignedInt0(b);

        case T_FLOAT:
            int s = (int) parseUnsignedInt(b);
            BigDecimal bigDecimal = new BigDecimal(s).divide(new BigDecimal(10.0f));
            return bigDecimal.floatValue();

        case T_STRING:
            return DataParseUtil.parseString(b);

        case T_ENUM_0:
            return DataParseUtil.parseEnum(b);

        default:
            return DataParseUtil.parsePacket(b);
        }
    }

    public static byte[] reverse(byte[] data) {
        byte[] b = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            b[i] = data[data.length - i - 1];
        }
        return b;
    }

    public static char parseUnsignedChar(byte[] b) {
        return (char) b[0];
    }

    public static String parseString(byte[] b) {
        return new String(b);
    }

    public static String parseEnum(byte[] b) {
        return EncryptUtils.bytesToHexString(b);
    }

    public static String parsePacket(byte[] b) {
        return EncryptUtils.bytesToHexString(b);
    }

    public static short parseSignedShort(byte[] b) {
        return (short) (((b[1] << 8) | b[0] & 0xff));
    }

    public static short parseUnsignedShort(byte[] b) {
        short data = parseSignedShort(b);
        return (short) (data & 0x0FFFF);
    }

    public static int parseSignedInt0(byte[] bytes) {
        int i;
        i = (int) ((bytes[0] & 0xff) | ((bytes[1] & 0xff) << 8) | ((bytes[2] & 0xff) << 16)
                | ((bytes[3] & 0xff) << 24));
        return i;
    }

    public static byte[] intToBytes(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (i & 0xff);
        bytes[1] = (byte) ((i >> 8) & 0xff);
        bytes[2] = (byte) ((i >> 16) & 0xff);
        bytes[3] = (byte) ((i >> 24) & 0xff);
        return bytes;
    }

    public static int parseUnsignedInt(byte[] b) {
        int n = parseSignedInt0(b);
        return n & 0xFFFFFFFF;
    }

    public static int parseSignedInt(byte[] b) {
        return ((b[1] << 8) | b[0] & 0xff);
    }

    public static long parseSignedLong(byte[] b) {
        return ((((long) b[7] & 0xff) << 56) | (((long) b[6] & 0xff) << 48) | (((long) b[5] & 0xff) << 40)
                | (((long) b[4] & 0xff) << 32) | (((long) b[3] & 0xff) << 24) | (((long) b[2] & 0xff) << 16)
                | (((long) b[1] & 0xff) << 8) | (((long) b[0] & 0xff) << 0));
    }

    public static float parseFloat(byte[] b) {
        int l;
        l = b[0];
        l &= 0xff;
        l |= ((long) b[1] << 8);
        l &= 0xffff;
        l |= ((long) b[2] << 16);
        l &= 0xffffff;
        l |= ((long) b[3] << 24);
        return Float.intBitsToFloat(l);
    }

    /**
     * 浮点转换为字节
     * 
     * @param f
     * @return
     */
    public static byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }

        return dest;

    }

    /**
     * 字节转换为浮点
     * 
     * @param b     字节（至少4个字节）
     * @param index 开始位置
     * @return
     */
    public static float byte2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }
}

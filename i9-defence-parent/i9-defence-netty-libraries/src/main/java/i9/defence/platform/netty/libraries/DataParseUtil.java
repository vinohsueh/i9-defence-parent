package i9.defence.platform.netty.libraries;

public class DataParseUtil {
    
    public static Object parseDataValue(byte type, byte[] data) {
        byte[] b = reverse(data);
        DataEnum dataEnum = DataEnum.valueOf00(type);
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
            return DataParseUtil.parseSignedLong(b);
            
        case T_FLOAT:
            return DataParseUtil.parseFloat(b);
            
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
        i = (int) ((bytes[0] & 0xff) | ((bytes[1] & 0xff) << 8) 
        | ((bytes[2] & 0xff) << 16) | ((bytes[3] & 0xff) << 24)); 
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
    
    public static void main(String[] args) {
        byte[] b = EncryptUtils.hexStringToBytes("0A000000");
        System.out.println(EncryptUtils.bytesToHexString(b));
    }
    
    public static int parseUnsignedInt(byte[] b) {
        int n = parseSignedInt0(b);
        return n & 0xFFFFFFFF;
    }
    
    public static int parseSignedInt(byte[] b) {
        return ((b[1] << 8) | b[0] & 0xff);
    }
    
    public static long parseSignedLong(byte[] b) {
        return ((((long) b[7] & 0xff) << 56) 
                | (((long) b[6] & 0xff) << 48) 
                | (((long) b[5] & 0xff) << 40) 
                | (((long) b[4] & 0xff) << 32) 
                | (((long) b[3] & 0xff) << 24) 
                | (((long) b[2] & 0xff) << 16) 
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
}

package i9.defence.platform.socket.util;

public class DataParseUtil {
    
    public static Object parseDataValue(short t, byte[] b) {
        DataEnum dataEnum = DataEnum.valueOf00(t);
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
            
        case T_SIGNED_INT:
            return DataParseUtil.parseSignedInt(b);
            
        default:
            return DataParseUtil.parsePacket(b);
        }
    }

    public static char parseUnsignedChar(byte[] b) {
        return (char) b[0];
    }
    
    public static String parseString(byte[] b) {
        return new String(b);
    }
    
    public static String parseEnum(byte[] b) {
        return new String(b);
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
    
    public static int parseSignedInt0(byte[] b) {
        return (int) ((((b[3] & 0xff) << 24) | ((b[2] & 0xff) << 16) | ((b[1] & 0xff) << 8) | ((b[0] & 0xff) << 0)));
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

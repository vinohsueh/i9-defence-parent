package i9.defence.platform.netty.libraries;

import java.util.HashMap;

public enum DataEnum {
    
    T_ENUM((short) 0), T_UNSIGNED_CHAR((short) 1), T_UNSIGNED_SHORT((short) 2), T_UNSIGNED_INT((short) 3), 
    
    T_SIGNED_LONG((short) 4), T_FLOAT((short) 5), T_STRING((short) 6), T_ENUM_0((short) 7), 
    
    T_PACKET((short) 8), 
    
    T_SIGNED_INT((short) 9);
    
    public short value;

    DataEnum(short value) {
        this.value = value;
    }
    
    private static final HashMap<Short, DataEnum> types = new HashMap<Short, DataEnum>();
    
    static {
        for (DataEnum dataEnum : values()) {
            types.put(dataEnum.value, dataEnum);
        }
    }

    public static DataEnum valueOf00(short value) {
        return types.get(value);
    }
}

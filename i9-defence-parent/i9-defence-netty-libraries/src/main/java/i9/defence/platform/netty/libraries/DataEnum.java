package i9.defence.platform.netty.libraries;

import java.util.HashMap;

public enum DataEnum {

    T_ENUM((byte) 0), T_UNSIGNED_CHAR((byte) 1), T_UNSIGNED_SHORT((byte) 2), T_UNSIGNED_INT((byte) 3),

    T_SIGNED_LONG((byte) 4), T_FLOAT((byte) 5), T_STRING((byte) 6), T_ENUM_0((byte) 7),

    T_PACKET((byte) 8);

    public byte value;

    DataEnum(byte value) {
        this.value = value;
    }

    private static final HashMap<Byte, DataEnum> types = new HashMap<Byte, DataEnum>();

    static {
        for (DataEnum dataEnum : values()) {
            types.put(dataEnum.value, dataEnum);
        }
    }

    public static DataEnum valueOf00(Byte value) {
        return types.get(value);
    }
}

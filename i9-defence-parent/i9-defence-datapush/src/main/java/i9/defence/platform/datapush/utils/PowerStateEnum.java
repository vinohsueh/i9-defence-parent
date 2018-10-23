package i9.defence.platform.datapush.utils;

import java.util.HashMap;

/**
 * 设备状态枚举类
 * 
 * @author jiangtao
 */
public enum PowerStateEnum {

    DEVICE_ONLINE(0, "设备下线"), DEVICE_OFFLINE(1, "设备上线");

    private final int value;

    private final String name;

    PowerStateEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    private static final HashMap<Integer, PowerStateEnum> types = new HashMap<Integer, PowerStateEnum>();

    static {
        for (PowerStateEnum powerStateEnum : values()) {
            types.put(powerStateEnum.getValue(), powerStateEnum);
        }
    }

    public static PowerStateEnum valueOf(int i) {
        return types.get(i);
    }
}

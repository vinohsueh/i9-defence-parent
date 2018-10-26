package i9.defence.platform.datapush.utils;

import java.util.HashMap;

/**
 * 设备状态枚举类
 * 
 * @author jiangtao
 */
public enum PowerStateEnum {

    /**
     * 设备上线
     */
    DEVICE_OFFLINE(1, "在线", "正常", "#00B050"),

    /**
     * 设备下线
     */
    DEVICE_ONLINE(2, "下线", "离线", "#808080"),

    /**
     * 设备上线
     */
    BATTERY_LOW(3, "低电量", "异常", "#FFC000"),

    /**
     * 设备上线
     */
    ERROR(4, "设备报警", "报警", "#FF0000");

    private final int value;

    private final String name;

    private final String desc;

    private final String color;

    public String getColor() {
        return color;
    }

    PowerStateEnum(int value, String name, String desc, String color) {
        this.value = value;
        this.name = name;
        this.desc = desc;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    private static final HashMap<Integer, PowerStateEnum> ENUM_TYPE = new HashMap<Integer, PowerStateEnum>();

    static {
        for (PowerStateEnum powerStateEnum : values()) {
            ENUM_TYPE.put(powerStateEnum.getValue(), powerStateEnum);
        }
    }

    public static PowerStateEnum valueOf(int i) {
        return ENUM_TYPE.get(i);
    }
}

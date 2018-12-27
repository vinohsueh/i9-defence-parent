package i9.defence.platform.microservice.mq.util;

import java.util.HashMap;

public enum DeviceAlarmSpecialEnum {

    ALARM(1, "报警"), FAULT(2, "故障"), ABNORMAL(3, "异常");

    DeviceAlarmSpecialEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    private final int index;

    private final String name;

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    private static final HashMap<Integer, DeviceAlarmSpecialEnum> types = new HashMap<Integer, DeviceAlarmSpecialEnum>();

    static {
        for (DeviceAlarmSpecialEnum deviceAlarmSpecialEnum : values()) {
            types.put(deviceAlarmSpecialEnum.getIndex(), deviceAlarmSpecialEnum);
        }
    }

    public static DeviceAlarmSpecialEnum valueOf(int index) {
        return types.get(index);
    }
}

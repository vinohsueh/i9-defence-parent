package i9.defence.platform.datapush.utils;

import java.util.HashMap;

/**
 * 消息类型
 * 
 * @author jiangtao
 * @date 2018-10-
 */
public enum TypeEnum {

    UPLOAD_DATA_POINT(1, "设备上传数据点消息"), UPLOAD_POWER_STATE(2, "设备上下线消息");

    private final int value;

    private final String name;

    TypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    private static final HashMap<Integer, TypeEnum> types = new HashMap<Integer, TypeEnum>();

    static {
        for (TypeEnum typeEnum : values()) {
            types.put(typeEnum.getValue(), typeEnum);
        }
    }

    public static TypeEnum valueOf(int i) {
        return types.get(i);
    }
}

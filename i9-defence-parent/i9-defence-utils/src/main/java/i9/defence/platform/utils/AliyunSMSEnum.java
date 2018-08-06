package i9.defence.platform.utils;

import java.util.ArrayList;
import java.util.List;

public enum AliyunSMSEnum {

    templateA("kehu", "mss_aa", true), backA("", "", false);

    private final String name;

    public String getName() {
        return name;
    }

    private final String templateNum;

    private final boolean show;

    public String getTemplateNum() {
        return templateNum;
    }

    public boolean isShow() {
        return show;
    }

    public static List<AliyunSMSEnum> getShowMenuList() {
        List<AliyunSMSEnum> list = new ArrayList<AliyunSMSEnum>();
        for (AliyunSMSEnum aliyunSMSEnum : values()) {
            if (aliyunSMSEnum.isShow()) {
                list.add(aliyunSMSEnum);
            }
        }
        return list;
    }

    AliyunSMSEnum(String name, String templateNum, boolean show) {
        this.templateNum = templateNum;
        this.show = show;
        this.name = name;
    }
}

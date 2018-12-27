package i9.defence.platform.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 阿里云短信模板id
 * 
 * @ClassName: AliyunSMSEnum
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月7日 上午9:41:43
 */
public enum AliyunSMSEnum {

    CUSTOMER("客户问候模板", "SMS_142050264", true), HIDDENDANGER("设备隐患模板", "SMS_143716483", true),
    OUTOFLINE("设备离线模板", "SMS_143716484", true), WANING("设备报警模板", "SMS_143716480", true);

    private final String name;

    private final String templateNum;

    private final boolean show;

    public String getTemplateNum() {
        return templateNum;
    }

    public boolean isShow() {
        return show;
    }

    public String getName() {
        return name;
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

    public static List<String> getShowMenuListName() {
        List<String> list = new ArrayList<String>();
        for (AliyunSMSEnum aliyunSMSEnum : values()) {
            if (aliyunSMSEnum.isShow() && aliyunSMSEnum.name.equals("客户问候模板")) {
                list.add(aliyunSMSEnum.getName());
            }
        }
        return list;
    }

    public static AliyunSMSEnum getAliyunSMSEnumByName(String name) {
        for (AliyunSMSEnum aliyunSMSEnum : values()) {
            if (aliyunSMSEnum.getName().equals(name) && aliyunSMSEnum.isShow()) {
                return aliyunSMSEnum;
            }
        }
        return null;
    }

    AliyunSMSEnum(String name, String templateNum, boolean show) {
        this.templateNum = templateNum;
        this.show = show;
        this.name = name;
    }
}

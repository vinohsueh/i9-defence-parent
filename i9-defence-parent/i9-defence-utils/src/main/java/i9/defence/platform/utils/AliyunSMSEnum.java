package i9.defence.platform.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 阿里云短信模板id
 * @ClassName: AliyunSMSEnum 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月7日 上午9:41:43
 */
public enum AliyunSMSEnum {
	
	CUSTOMER("客户问候模板", "SMS_141200477", true), 
	HIDDENDANGER("设备隐患模板", "SMS_141195516", true),
	OUTOFLINE("设备离线模板","SMS_141195515",true),
	WANING("设备报警模板","SMS_141200479",true);
	
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
	
	public static AliyunSMSEnum getAliyunSMSEnumByTemplateNum(String TemplateNum ) {
		for(AliyunSMSEnum aliyunSMSEnum : values()) {
			if(aliyunSMSEnum.getTemplateNum().equals(TemplateNum)&& aliyunSMSEnum.isShow()) {
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

package i9.defence.platform.enums;

import org.apache.commons.lang.StringUtils;

/** 
* 创建时间：2018年3月28日 下午2:35:57
* @author  lby
* @version  
* 
*/
public enum ErrorAlertEnum {
	NORMAL("00000000","正常"),OPENC("00000001","开路"),SHORTC("00000002","短路"),OVERL("00000003","过流"),OVERT("00000004","过温");
	
	private ErrorAlertEnum(String id,String name){
		
	}

	private String id;
	
	private String name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getErrorName(String id){
		if (StringUtils.isBlank(id)) {
			return "未知故障";
		}
		for (ErrorAlertEnum errorAlertEnum : ErrorAlertEnum.values()) {
			if (errorAlertEnum.getId().equals(id)) {
				return errorAlertEnum.getName();
			}
		}
		return "未知故障";
	}
	
	
}

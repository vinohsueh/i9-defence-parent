package i9.defence.platform.service.dto;

/**
 * @ClassName: DeviceError 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月30日 上午9:25:54
 */
public class DeviceError {
	
	private String name;
	
	private String code;
	
	private int catagoryId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(int catagoryId) {
		this.catagoryId = catagoryId;
	}
	
	
}

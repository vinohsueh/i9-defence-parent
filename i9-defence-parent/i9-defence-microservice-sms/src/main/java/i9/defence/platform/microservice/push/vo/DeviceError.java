package i9.defence.platform.microservice.push.vo;
/** 
* 创建时间：2018年5月22日 下午2:59:41
* @author  lby
* @version  
* 
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

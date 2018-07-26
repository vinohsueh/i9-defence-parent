package i9.defence.platform.microservice.push.vo;
/** 
* 创建时间：2018年5月22日 上午11:25:57
* @author  lby
* @version  
* 
*/
public class DeviceInfoDto {
	
	private int id;
	
	private String remarks;
	
	private String error;
	
	private int equipmentId;
	
	
	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "DeviceInfoDto [id=" + id + ", remarks=" + remarks + ", error=" + error + "]";
	}
	
}

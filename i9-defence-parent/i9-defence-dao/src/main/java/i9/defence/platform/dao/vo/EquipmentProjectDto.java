package i9.defence.platform.dao.vo;

import java.util.Date;

import i9.defence.platform.utils.StringUtil;

/** 
* 创建时间：2018年5月16日 上午9:46:06
* @author  lby
* @version  
* 
*/
public class EquipmentProjectDto {
	
	private Date equipmentDate;
	
	private String clientPhone;
	
	private String clientName;
	
	private String safePhone;
	
	private String safeName;
	
	private String deviceId;
	
	private String systemId;
	
	private Integer projectId;
	
	private String equipmentPosition;
	
	private String equipmentRemarks;
	
	private String equipmentName;
	
	private String equipmentCategoryName;
	
	private String projectName;
	
	private String projectLocation;
	
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public Date getEquipmentDate() {
		return equipmentDate;
	}

	public void setEquipmentDate(Date equipmentDate) {
		this.equipmentDate = equipmentDate;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getSafePhone() {
		return safePhone;
	}

	public void setSafePhone(String safePhone) {
		this.safePhone = safePhone;
	}

	public String getSafeName() {
		return safeName;
	}

	public void setSafeName(String safeName) {
		this.safeName = safeName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getEquipmentPosition() {
		return equipmentPosition;
	}

	public void setEquipmentPosition(String equipmentPosition) {
		this.equipmentPosition = equipmentPosition;
	}

	public String getEquipmentRemarks() {
		return equipmentRemarks;
	}

	public void setEquipmentRemarks(String equipmentRemarks) {
		this.equipmentRemarks = equipmentRemarks;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentCategoryName() {
		return equipmentCategoryName;
	}

	public void setEquipmentCategoryName(String equipmentCategoryName) {
		this.equipmentCategoryName = equipmentCategoryName;
	}
	
	public String getDateString(){
		return StringUtil.dateToString(equipmentDate);
	}
	
}

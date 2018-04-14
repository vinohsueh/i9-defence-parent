package i9.defence.platform.dao.vo;

import java.io.Serializable;

public class EquipmentSearchDto extends PageListDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1166000316912439837L;
	//数据库ORDER BY 排序
	private String orderByClause;

	private String equipmentName;
	 
	private String equipmentIdentifier;
	
	private String equipmentPosition;
	
	private Integer distributorId;
	
	private Integer projectId;
	
	private String deviceId;
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(Integer distributorId) {
		this.distributorId = distributorId;
	}

	/**
	 * @return the orderByClause
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * @param orderByClause the orderByClause to set
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * @return the equipmentName
	 */
	public String getEquipmentName() {
		return equipmentName;
	}

	/**
	 * @param equipmentName the equipmentName to set
	 */
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	/**
	 * @return the equipmentIdentifier
	 */
	public String getEquipmentIdentifier() {
		return equipmentIdentifier;
	}

	/**
	 * @param equipmentIdentifier the equipmentIdentifier to set
	 */
	public void setEquipmentIdentifier(String equipmentIdentifier) {
		this.equipmentIdentifier = equipmentIdentifier;
	}

	/**
	 * @return the equipmentPosition
	 */
	public String getEquipmentPosition() {
		return equipmentPosition;
	}

	/**
	 * @param equipmentPosition the equipmentPosition to set
	 */
	public void setEquipmentPosition(String equipmentPosition) {
		this.equipmentPosition = equipmentPosition;
	}

	
}

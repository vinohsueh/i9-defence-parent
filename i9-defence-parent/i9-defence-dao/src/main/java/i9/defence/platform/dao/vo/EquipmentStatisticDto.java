package i9.defence.platform.dao.vo;

/**
 * 设备统计dto
 * @ClassName: EquipmentStatisticDto 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年5月8日 下午3:48:25
 */
public class EquipmentStatisticDto {
	
	/**
	 * 设备数量
	 */
	private  Integer equipCount;
	
	/**
	 * 设备类型名称
	 */
	private  String eqCategoryName;
	
	/**
	 * 项目地址
	 */
	private String projectAddress;
	
	/**
	 * 建筑面积
	 */
	private Double projectArea;
	
	/**
	 * 项目负责人Id
	 */
	private Integer distributorId;
	
	/**
	 * 项目负责人名称
	 */
	private String mangerName;

	public Integer getEquipCount() {
		return equipCount;
	}

	public void setEquipCount(Integer equipCount) {
		this.equipCount = equipCount;
	}

	public String getEqCategoryName() {
		return eqCategoryName;
	}

	public void setEqCategoryName(String eqCategoryName) {
		this.eqCategoryName = eqCategoryName;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public Double getProjectArea() {
		return projectArea;
	}

	public void setProjectArea(Double projectArea) {
		this.projectArea = projectArea;
	}

	public Integer getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(Integer distributorId) {
		this.distributorId = distributorId;
	}

	public String getMangerName() {
		return mangerName;
	}

	public void setMangerName(String mangerName) {
		this.mangerName = mangerName;
	}
	
	
}

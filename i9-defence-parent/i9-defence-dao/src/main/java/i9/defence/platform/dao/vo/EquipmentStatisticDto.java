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
	 * 所有设备个数
	 */
	private Integer allEquipCount;
	
	
	public Integer getAllEquipCount() {
		return allEquipCount;
	}

	public void setAllEquipCount(Integer allEquipCount) {
		this.allEquipCount = allEquipCount;
	}

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
	
}

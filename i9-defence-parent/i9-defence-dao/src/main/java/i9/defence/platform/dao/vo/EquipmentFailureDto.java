package i9.defence.platform.dao.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * EquipmentFailureDto
* @ClassName: EquipmentFailureDto 
* @Description: TODO
* @author luobo
* @date 2018年1月9日 上午9:44:44 
*
 */
public class EquipmentFailureDto {
	
	/**
	 * 处理人
	 */
	@NotBlank(message="处理人不能为空")
	private String conductor;
	
	/**
	 * 处理状态
	 */
	@NotBlank(message="处理状态不能为空")
	private int dealState;

	public String getConductor() {
		return conductor;
	}

	public void setConductor(String conductor) {
		this.conductor = conductor;
	}

	public int getDealState() {
		return dealState;
	}

	public void setDealState(int dealState) {
		this.dealState = dealState;
	}
	
}

package i9.defence.platform.dao.vo;

import java.util.List;

import i9.defence.platform.model.Passageway;

/** 
* 创建时间：2018年4月12日 上午11:27:22
* @author  lby
* @version  
* 
*/
public class PassagewayDto {
	
	private List<Passageway> Passageways;
	
	private Integer equipmentId;

	public List<Passageway> getPassageways() {
		return Passageways;
	}

	public void setPassageways(List<Passageway> passageways) {
		Passageways = passageways;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	
}

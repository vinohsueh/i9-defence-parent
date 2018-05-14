package i9.defence.platform.dao.vo;

import i9.defence.platform.model.Passageway;

/** 
* 创建时间：2018年4月12日 上午11:27:22
* @author  lby
* @version  
* 
*/
public class PassagewayDto {
	
	private Passageway [] passageways;
	
	private Integer categoryId;

	

	public Passageway[] getPassageways() {
		return passageways;
	}

	public void setPassageways(Passageway[] passageways) {
		this.passageways = passageways;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	

	

	
}

package i9.defence.platform.dao.vo;

import java.util.Date;

/** 
* @author user: jiace
* @version creatTime：2018年4月4日 下午4:50:15 
* 
*/
public class HiddenDangerSearchDto extends PageListDto{

	//数据库ORDER BY 排序
    private String orderByClause;
	/*
	 * 设备穿件日期
	 */
	private Date equipmentDate;
	/*
	 * 设备位置
	 */
	private String equipmentPosition;
	/*
	 * 项目名称
	 */
	private String projectName;
	public String getOrderByClause() {
		return orderByClause;
	}
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	public Date getEquipmentDate() {
		return equipmentDate;
	}
	public void setEquipmentDate(Date equipmentDate) {
		this.equipmentDate = equipmentDate;
	}
	public String getEquipmentPosition() {
		return equipmentPosition;
	}
	public void setEquipmentPosition(String equipmentPosition) {
		this.equipmentPosition = equipmentPosition;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
 
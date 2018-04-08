package i9.defence.platform.dao.vo;

import java.util.Date;

import i9.defence.platform.utils.StringUtil;

/** 
* @author user: jiace
* @version creatTime：2018年4月4日 下午4:50:36 
* 
*/

public class HiddenDangerDto {

	/*
	 * 设备id
	 */
	private Integer id;
	/*
	 * 设备名称
	 */
	private String equipmentName;
	/*
	 * 设备位置
	 */
	private String equipmentPosition;
	/*
	 * 设备穿件日期
	 */
	private Date equipmentDate;
	/*
	 * 设备编号
	 */
	private String systemId;
	/*
	 * 项目名称
	 */
	private String projectName;
	/*
	 * 设备隐患数量
	 */
	private Integer hiddeCount;
	/*
	 * 设备报警数据
	 */
	private Integer warningCount;
	
	public Date getEquipmentDate() {
		return equipmentDate;
	}
	public void setEquipmentDate(Date equipmentDate) {
		this.equipmentDate = equipmentDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentPosition() {
		return equipmentPosition;
	}
	public void setEquipmentPosition(String equipmentPosition) {
		this.equipmentPosition = equipmentPosition;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getHiddeCount() {
		return hiddeCount;
	}
	public void setHiddeCount(Integer hiddeCount) {
		this.hiddeCount = hiddeCount;
	}
	public Integer getWarningCount() {
		return warningCount;
	}
	public void setWarningCount(Integer warningCount) {
		this.warningCount = warningCount;
	}

	public String getEquipmentDateStr() {
		if (equipmentDate != null) {
			return StringUtil.dateToString(equipmentDate);
		}
		return "";
	}
}
 
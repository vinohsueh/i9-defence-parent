package i9.defence.platform.dao.vo;

import java.util.List;

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
	 * 设备编号
	 */
	private String deviceId;
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
	/*
	 * 设备类型
	 */
	private String eqCategoryName;
	/*
	 * 安全负责人
	 */
	private String personLiableMan;
	
	private Integer status;
	
	/**
	 * 数据状态
	 */
	private Integer dataStatus;
	
	/**
	 * 旧数据状态
	 */
	private Integer remainAlert;
	
	private List<EqChannelDataDto> eqChannelDataList;
	
	/**
	 * 设备备注
	 */
	private String equipmentRemarks;
	
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 时间
	 */
	private String newestTime;
	
	public Integer getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}
	
	public Integer getRemainAlert() {
		return remainAlert;
	}
	public void setRemainAlert(Integer remainAlert) {
		this.remainAlert = remainAlert;
	}
	public String getNewestTime() {
		return newestTime;
	}
	public void setNewestTime(String newestTime) {
		this.newestTime = newestTime;
	}
	
	public String getEquipmentRemarks() {
		return equipmentRemarks;
	}
	public void setEquipmentRemarks(String equipmentRemarks) {
		this.equipmentRemarks = equipmentRemarks;
	}
	public String getPersonLiableMan() {
		return personLiableMan;
	}
	public void setPersonLiableMan(String personLiableMan) {
		this.personLiableMan = personLiableMan;
	}
	public String getEqCategoryName() {
		return eqCategoryName;
	}
	public void setEqCategoryName(String eqCategoryName) {
		this.eqCategoryName = eqCategoryName;
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
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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
	public List<EqChannelDataDto> getEqChannelDataList() {
		return eqChannelDataList;
	}
	public void setEqChannelDataList(List<EqChannelDataDto> eqChannelDataList) {
		this.eqChannelDataList = eqChannelDataList;
	}
	
	private String channelName;

    private String channelValue;
    
    
    private String datastatustr;
    public String getChannelName() {
        return channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public String getChannelValue() {
        return channelValue;
    }
    public void setChannelValue(String channelValue) {
        this.channelValue = channelValue;
    }
    public String getDatastatustr() {
        return datastatustr;
    }
    public void setDatastatustr(String datastatustr) {
        this.datastatustr = datastatustr;
    }
    
    
   
    
    
}
 
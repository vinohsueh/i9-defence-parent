package i9.defence.platform.dao.vo;

import java.util.Date;

import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.Project;

/**
 * ApplyDto
 * @ClassName: ApplyDto 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年4月18日 上午9:32:51
 */
public class ApplyDto {
	 private Integer id;
	    
	    /**
	     * 申请类型(0:删除项目,1:删除设备)
	     */
	    private Integer type;
	    
	    /**
	     * 申请状态(0:未处理,1:已处理)
	     */
	    private Integer state;
	    
	    /**
	     * 申请人Id
	     */
	    private Integer applyId;
	    
	    /**
	     * 申请处理时间
	     */
	    private Date applyDate;
	    
	    /**
	     * 处理人Id
	     */
	    private Integer conductorId;
	    
	    /**
	     * 处理时间
	     */
	    private Date conductDate;
	    
	    /**
	     * 关联申请用户
	     */
	    private Manager applyManager;
	    
	    /**
	     * 关联处理用户
	     */
	    private Manager conductorManager;
	    
	    /**
	     * 设备Id
	     */
	    private Integer equipmentId;
	    
	    /**
	     * 关联设备表
	     */
	    private Equipment equipment;
	    
	    /**
	     * 项目Id
	     */
	    private Integer projectId;
	    
	    /**
	     * 关联项目表
	     */
	    private Project project;
	    
	    /**
	     * 申请表中项目的负责人的父Id
	     */
	    private Integer ConductParentId;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Integer getState() {
			return state;
		}

		public void setState(Integer state) {
			this.state = state;
		}

		public Integer getApplyId() {
			return applyId;
		}

		public void setApplyId(Integer applyId) {
			this.applyId = applyId;
		}

		public Date getApplyDate() {
			return applyDate;
		}

		public void setApplyDate(Date applyDate) {
			this.applyDate = applyDate;
		}

		public Integer getConductorId() {
			return conductorId;
		}

		public void setConductorId(Integer conductorId) {
			this.conductorId = conductorId;
		}

		public Date getConductDate() {
			return conductDate;
		}

		public void setConductDate(Date conductDate) {
			this.conductDate = conductDate;
		}

		public Manager getApplyManager() {
			return applyManager;
		}

		public void setApplyManager(Manager applyManager) {
			this.applyManager = applyManager;
		}

		public Manager getConductorManager() {
			return conductorManager;
		}

		public void setConductorManager(Manager conductorManager) {
			this.conductorManager = conductorManager;
		}

		public Integer getEquipmentId() {
			return equipmentId;
		}

		public void setEquipmentId(Integer equipmentId) {
			this.equipmentId = equipmentId;
		}

		public Equipment getEquipment() {
			return equipment;
		}

		public void setEquipment(Equipment equipment) {
			this.equipment = equipment;
		}

		public Integer getProjectId() {
			return projectId;
		}

		public void setProjectId(Integer projectId) {
			this.projectId = projectId;
		}

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

		public Integer getConductParentId() {
			return ConductParentId;
		}

		public void setConductParentId(Integer conductParentId) {
			ConductParentId = conductParentId;
		}
	    
}

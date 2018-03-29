package i9.defence.platform.model;

import java.util.Date;

import i9.defence.platform.utils.StringUtil;

public class Equipment {
	private Integer id;
    /**
     * 设备名称
     */
    private String equipmentName;
    /**
     * 设备数量
     */
    private Integer equipmentNum;
    /**
     * 设备编号
     */
    private String equipmentIdentifier;
    /**
     * 设备位置
     */
    private String equipmentPosition;
    /**
     * 设备创建时间
     */
    private Date equipmentDate;
    /**
     * 设备备注
     */
    private String equipmentRemarks;
    /**
     * 设备分类id
     */
    private Integer equipmentCategoryId;
    /**
     * 设备分类(一对一)
     */
    private EquipmentCategory equipmentCategory;
    
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 项目(一对一)
     */
    private Project project;
    
    /**
     * 系统id
     */
    private String systemId;
    /**
     * 装置数量
     */
    private Integer unit;
    /**
     * 回路
     */
    private Integer loopl;
    /**
     * 删除状态
     */
    private Integer delStatus;

    public EquipmentCategory getEquipmentCategory() {
		return equipmentCategory;
	}

	public void setEquipmentCategory(EquipmentCategory equipmentCategory) {
		this.equipmentCategory = equipmentCategory;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName == null ? null : equipmentName.trim();
    }

    public Integer getEquipmentNum() {
        return equipmentNum;
    }

    public void setEquipmentNum(Integer equipmentNum) {
        this.equipmentNum = equipmentNum;
    }

    public String getEquipmentIdentifier() {
        return equipmentIdentifier;
    }

    public void setEquipmentIdentifier(String equipmentIdentifier) {
        this.equipmentIdentifier = equipmentIdentifier == null ? null : equipmentIdentifier.trim();
    }

    public String getEquipmentPosition() {
        return equipmentPosition;
    }

    public void setEquipmentPosition(String equipmentPosition) {
        this.equipmentPosition = equipmentPosition == null ? null : equipmentPosition.trim();
    }

    public Date getEquipmentDate() {
        return equipmentDate;
    }

    public void setEquipmentDate(Date equipmentDate) {
        this.equipmentDate = equipmentDate;
    }

    public String getEquipmentRemarks() {
        return equipmentRemarks;
    }

    public void setEquipmentRemarks(String equipmentRemarks) {
        this.equipmentRemarks = equipmentRemarks == null ? null : equipmentRemarks.trim();
    }

    public Integer getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    public void setEquipmentCategoryId(Integer equipmentCategoryId) {
        this.equipmentCategoryId = equipmentCategoryId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getLoopl() {
        return loopl;
    }

    public void setLoopl(Integer loopl) {
        this.loopl = loopl;
    }
    public String getEquipmentDateStr() {
    	if(equipmentDate!=null) {
    		return StringUtil.dateToString(equipmentDate);
    	}
        return "";
    }

	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
    
}
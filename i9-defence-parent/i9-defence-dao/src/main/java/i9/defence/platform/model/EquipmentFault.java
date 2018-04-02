package i9.defence.platform.model;

public class EquipmentFault {
    /**
     * id
     */
    private Integer id;
    /**
     * 故障名称
     */
    private String name;
    /**
     * 故障代码
     */
    private String code;
    /**
     * 设备id
     */
    private Integer equipmentId;
    /**
     * 设备类型名称
     */
    private String equipmentName;
    /**
     * 设备类型编号
     */
    private String eqCategoryId;
    
    
    public String getEqCategoryId() {
		return eqCategoryId;
	}

	public void setEqCategoryId(String eqCategoryId) {
		this.eqCategoryId = eqCategoryId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }
}
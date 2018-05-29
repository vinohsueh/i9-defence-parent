package i9.defence.platform.model;

import i9.defence.platform.enums.FaultTypeEnum;

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
    
    /**
     * 0：报警 1：故障
     */
    private Integer type;
    
    /**
     * 1:过流激活 2：过温激活
     */
    private Integer jhType;
    
    /**
     * 过流激活
     */
    private String activationOne;
    
    /**
     * 过流激活
     */
    private String activationTwo;
    
    
    public Integer getJhType() {
		return jhType;
	}

	public void setJhType(Integer jhType) {
		this.jhType = jhType;
	}


	public String getActivationOne() {
		return activationOne;
	}

	public void setActivationOne(String activationOne) {
		this.activationOne = activationOne;
	}

	public String getActivationTwo() {
		return activationTwo;
	}

	public void setActivationTwo(String activationTwo) {
		this.activationTwo = activationTwo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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
    
    
    public String getTypeName(){
    	return FaultTypeEnum.getNameById(type);
    }
}
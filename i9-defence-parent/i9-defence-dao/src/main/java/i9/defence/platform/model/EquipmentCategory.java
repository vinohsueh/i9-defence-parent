package i9.defence.platform.model;
/**
 * 设备分类实体
 * 
 * 
 * @author 
 *
 * @create 
 *
 */
public class EquipmentCategory {

	private Integer id;
    /**
     * 设备分类名称
     */
    private String eqCategoryName;
    /**
     * 设备分类编号
     */
    private String eqCategoryId;
    /**
     * 设备分类说明
     */
    private String eqCategoryExplain;
    
    private Integer eqNum;
    
    private Integer eqNums;
    
    private Integer projectId;
    //类型类型
//    private String systemCategory;
    
    public Integer getProjectId() {
		return projectId;
	}
 
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
//    public String getSystemCategory() {
//		return systemCategory;
//	}
//
//	public void setSystemCategory(String systemCategory) {
//		this.systemCategory = systemCategory;
//	}

	/**
     * 设备分类一级id
     */
    private Integer eqSystemtypeId;
    
    private EquipmentSystemtype equipmentSystemtype;
    
    public EquipmentSystemtype getEquipmentSystemtype() {
		return equipmentSystemtype;
	}

	public void setEquipmentSystemtype(EquipmentSystemtype equipmentSystemtype) {
		this.equipmentSystemtype = equipmentSystemtype;
	}

	public Integer getEqSystemtypeId() {
		return eqSystemtypeId;
	}

	public void setEqSystemtypeId(Integer eqSystemtypeId) {
		this.eqSystemtypeId = eqSystemtypeId;
	}

	public Integer getEqNums() {
		return eqNums;
	}

	public void setEqNums(Integer eqNums) {
		this.eqNums = eqNums;
	}

	/*
	 * 项目负责人
	 */
	private Integer prijrctManagerId;
	/*
	 * 经销商
	 */
	private Integer distributorId;
    

    public Integer getEqNum() {
		return eqNum;
	}

	public void setEqNum(Integer eqNum) {
		this.eqNum = eqNum;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEqCategoryName() {
        return eqCategoryName;
    }

    public void setEqCategoryName(String eqCategoryName) {
        this.eqCategoryName = eqCategoryName == null ? null : eqCategoryName.trim();
    }

	public String getEqCategoryId() {
		return eqCategoryId;
	}

	public void setEqCategoryId(String eqCategoryId) {
		this.eqCategoryId = eqCategoryId;
	}

	public String getEqCategoryExplain() {
		return eqCategoryExplain;
	}

	public void setEqCategoryExplain(String eqCategoryExplain) {
		this.eqCategoryExplain = eqCategoryExplain;
	}

	public Integer getPrijrctManagerId() {
		return prijrctManagerId;
	}

	public void setPrijrctManagerId(Integer prijrctManagerId) {
		this.prijrctManagerId = prijrctManagerId;
	}

	public Integer getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(Integer distributorId) {
		this.distributorId = distributorId;
	}
    
}
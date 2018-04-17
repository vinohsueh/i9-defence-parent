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
    
}
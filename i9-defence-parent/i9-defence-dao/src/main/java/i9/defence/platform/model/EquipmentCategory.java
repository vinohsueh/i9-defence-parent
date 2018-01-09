package i9.defence.platform.model;
/**
 * 设备分类实体类
 * 
 * 
 * @author 
 *
 * @create 2017年12月26日
 *
 */
public class EquipmentCategory {
    private Integer id;
    /**
     * 设备分类名称
     */
    private String eqCategoryName;

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
}
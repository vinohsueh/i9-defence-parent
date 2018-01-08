package i9.defence.platform.model;

public class EquipmentCategory {
    private Integer id;

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
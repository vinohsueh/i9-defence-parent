package i9.defence.platform.model;

public class EquipmentSystemtype {
    private Integer id;
    //一级类别名称
    private String systemType;
    //类型类型
    private String systemCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType == null ? null : systemType.trim();
    }

    public String getSystemCategory() {
        return systemCategory;
    }

    public void setSystemCategory(String systemCategory) {
        this.systemCategory = systemCategory == null ? null : systemCategory.trim();
    }
}
package i9.defence.platform.model;

import java.util.List;

public class PageUrl {
    private Integer id;

    private String name;

    private String code;

    private Integer parentId;

    private Integer orderNumber;

    private Byte ifSingle;
    
    private String icon;
    
    private List<PageUrl> items;
    
    
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<PageUrl> getItems() {
        return items;
    }

    public void setItems(List<PageUrl> items) {
        this.items = items;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Byte getIfSingle() {
        return ifSingle;
    }

    public void setIfSingle(Byte ifSingle) {
        this.ifSingle = ifSingle;
    }
}
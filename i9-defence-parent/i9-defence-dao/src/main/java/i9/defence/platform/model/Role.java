package i9.defence.platform.model;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Role {
    private Integer id;
    
    @NotBlank(message="角色名称不能为空")
    @Size(max=10,min=2,message="角色名称长度2-10")
    private String name;
    
    @NotBlank(message="角色代码不能为空")
    @Size(max=20,min=2,message="角色代码长度2-20")
    private String code;
    
    private List<Integer> permissionIds;
    
    private List<Permission> permissions;

    private List<Integer> pageIds;
    
    
    public List<Integer> getPageIds() {
        return pageIds;
    }

    public void setPageIds(List<Integer> pageIds) {
        this.pageIds = pageIds;
    }

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }

    
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
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
}
package i9.defence.platform.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Permission {
    private Integer id;
    
    @NotBlank(message="权限名称不能为空")
    @Size(max=10,min=2,message="权限名称长度2-10")
    private String name;
    
    @NotBlank(message="权限代码不能为空")
    @Size(max=20,min=2,message="权限代码长度2-20")
    private String code;

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
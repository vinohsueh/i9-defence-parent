package i9.defence.platform.dao.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class ManagerUpdatePwdDto {
    private Integer id;

    @NotBlank(message = "密码不能为空")
    @Size(min = 5, max = 11, message = "请输入正确密码格式(5-11位)")
    private String oldPassword;

    @NotBlank(message = "密码不能为空")
    @Size(min = 5, max = 11, message = "请输入正确密码格式(5-11位)")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Size(min = 5, max = 11, message = "请输入正确密码格式(5-11位)")
    private String confirmPwd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }
    
}

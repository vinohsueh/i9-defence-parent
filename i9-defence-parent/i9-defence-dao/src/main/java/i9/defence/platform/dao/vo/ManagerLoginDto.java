package i9.defence.platform.dao.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/** 
 * 创建时间：2018年1月4日 上午11:04:21
 * @author  lby
 * @version  
 * 
 */
public class ManagerLoginDto {
    
    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空")
    @Length(min=5,max=20,message="账号在5-20字符之间")
    private String username;
    
    /**
     * 密码
     */
    @NotBlank(message="密码不能为空")
    @Length(min=5,max=20,message="密码在5-20字符之间")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}

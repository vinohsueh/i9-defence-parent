package i9.defence.platform.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import i9.defence.platform.utils.StringUtil;

/**
 * 管理员
 * 
 * 
 * @author lby
 *
 * @create 2018年1月4日
 *
 */
public class Manager {
    private Integer id;
    
    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空")
    @Length(max=30,min=5,message="用户名长度在5-20之间")
    private String username;
    
    /**
     * 密码
     */
    @NotBlank(message="密码不能为空")
    @Length(max=30,min=5,message="密码长度在5-40之间")
    private String password;
    
    /**
     * 确认密码
     */
    @NotBlank(message="确认密码不能为空")
    @Length(max=30,min=5,message="确认密码长度在5-40之间")
    private String confirmPwd;
    
    /**
     * 邮箱 @Pattern定义正则表达式
     */
    @Pattern(regexp="\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}",message="请输入正确的邮箱格式")
    private String email;
    
    /**
     * 电话
     */
    @NotBlank(message="电话号码不能为空")
    @Pattern(regexp="^1[3|4|5|7|8][0-9]\\d{8}$",message="请输入正确的手机号")
    private String phone;
    
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 账户名称
     */
    private String name;
    
    /**
     * 账户类型 (0:网站账户,1:账户)
     */
    private Byte type;
    
    /**
     * 地域
     */
    private String area;
    
    /**
     * 地址
     */
    private String address;
    
    /**
     * 是否安全责任人(0:否 1:是)
     */
    private Byte securitier;
    
    /**
     * 开启状态(0:关 1:开)
     */
    private Byte status;
    
    @NotNull(message="角色不能为空")
    private Role role;
    
    private Integer projectId;

    /**
     * 对于一级经销 此list存的二级经销商    对于二级经销商存的三级经销商
     * */
    private List<Manager> agencyList;
    
    private String projectName;
    
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public List<Manager> getAgencyList() {
        return agencyList;
    }

    public void setAgencyList(List<Manager> agencyList) {
        this.agencyList = agencyList;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getSecuritier() {
        return securitier;
    }

    public void setSecuritier(Byte securitier) {
        this.securitier = securitier;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getCreateTimeStr() {
    	if(createTime != null) {
    		return StringUtil.dateToStringWithoutTime(createTime);
    	}
        return "";
    }
}

package i9.defence.platform.model;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

/**
 * 用户注册申请
 * 
 * 
 * @author lby
 *
 * @create 2018年1月11日
 *
 */
public class ManagerApply {
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
     * 邮箱
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
    @Length(max=10,message="地域长度最大为10")
    private String area;
    
    /**
     * 地址
     */
    @Length(max=10,message="地址长度最大为100")
    private String address;
    
    /**
     * 是否安全责任人(0:否 1:是)
     */
    private Byte securitier;
    
    /**
     * 同意标记(0:未操作  1:同意,-1:拒绝)
     */
    private Byte agreeFlag;
    
    /**
     * 拒绝理由
     */
    private String refuseContent;
    
    /**
     * 项目id
     */
    private Integer projectId;
    
    /**
     * 操作人id
     */
    private Integer operaterId;
    
    @NotBlank(message="权限不能为空")
    private String roleName;
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getSecuritier() {
        return securitier;
    }

    public void setSecuritier(Byte securitier) {
        this.securitier = securitier;
    }

    public Byte getAgreeFlag() {
        return agreeFlag;
    }

    public void setAgreeFlag(Byte agreeFlag) {
        this.agreeFlag = agreeFlag;
    }

    public String getRefuseContent() {
        return refuseContent;
    }

    public void setRefuseContent(String refuseContent) {
        this.refuseContent = refuseContent == null ? null : refuseContent.trim();
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
    
    public Integer getOperaterId() {
        return operaterId;
    }

    public void setOperaterId(Integer operaterId) {
        this.operaterId = operaterId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Manager getManager(){
        Manager manager = new Manager();
        BeanUtils.copyProperties(this, manager);
        Role role = new Role();
        role.setName(roleName);
        manager.setId(null);
        manager.setRole(role);
        return manager;
    }
    
}
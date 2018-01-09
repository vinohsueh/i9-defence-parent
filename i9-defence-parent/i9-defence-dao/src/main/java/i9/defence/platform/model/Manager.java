package i9.defence.platform.model;

import java.util.Date;

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
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 电话
     */
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
}
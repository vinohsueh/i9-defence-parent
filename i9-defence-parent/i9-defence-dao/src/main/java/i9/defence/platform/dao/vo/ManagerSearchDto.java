package i9.defence.platform.dao.vo;
/** 
 * 创建时间：2018年1月4日 上午10:14:06
 * @author  lby
 * @version  
 * 
 */
public class ManagerSearchDto extends PageListDto{
    
    private String startTimeString;
    
    private String endTimeString;
    
    private String orderByClause;

    private Short type;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 电话
     */
    private String phone;
    /**
     * 账户名称
     */
    private String name;
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 是否安全责任人(0:否 1:是)
     */
    private Byte securitier;
    
    public Byte getSecuritier() {
		return securitier;
	}

	public void setSecuritier(Byte securitier) {
		this.securitier = securitier;
	}

	public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartTimeString() {
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    
    
}

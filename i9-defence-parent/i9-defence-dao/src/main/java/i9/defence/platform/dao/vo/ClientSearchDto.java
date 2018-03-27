package i9.defence.platform.dao.vo;

/**
* @author : JiaCe
* @version ：2018年1月11日下午3:11:39
* 客户dto
*/
public class ClientSearchDto extends PageListDto{

    /**
     * 邮箱
     */
    private String mail;
    
    /**
     * 电话
     */
    private String phone;
    /**
     * 账户名称
     */
    private String name;
    /**
     * 当前登录人
     */
    private Integer createId;
    
    private String orderByClause;

    public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    
    
}

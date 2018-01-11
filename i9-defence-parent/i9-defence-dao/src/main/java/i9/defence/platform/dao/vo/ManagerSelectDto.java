package i9.defence.platform.dao.vo;

import java.io.Serializable;

/**
 * 新建项目时候选择项目责任人和安全责任人用
 * @author 姜哲
 * @create 2018年1月8日
 */
public class ManagerSelectDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8228549231676005022L;
	
    private Integer id;
    
    //用户名
    private String username;

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
		this.username = username;
	}
    
    
}

package i9.defence.platform.dao.vo;


/**
 * 隐患设备Dto
* @ClassName: HiddenDangerDto 
* @Description: TODO
* @author luobo
* @date 2018年1月10日 上午10:28:17 
*
 */
public class HiddenDangerDto extends PageListDto {
	
	/**
	 *  @Fields serialVersionUID : TODO  
	 */
	/**
	 * 隐患名称
	 */
	private String name;
	
	private String orderByClause;
		
	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

 
	
}

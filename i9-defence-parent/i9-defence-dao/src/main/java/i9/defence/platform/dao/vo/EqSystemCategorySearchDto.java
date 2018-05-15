package i9.defence.platform.dao.vo;

import java.io.Serializable;

public class EqSystemCategorySearchDto extends PageListDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1166000316912439837L;
	//数据库ORDER BY 排序
	private String orderByClause;
	 //一级类别名称
    private String systemType;
    //类型
    private String systemCategory;
	public String getOrderByClause() {
		return orderByClause;
	}
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	public String getSystemCategory() {
		return systemCategory;
	}
	public void setSystemCategory(String systemCategory) {
		this.systemCategory = systemCategory;
	}
    
}

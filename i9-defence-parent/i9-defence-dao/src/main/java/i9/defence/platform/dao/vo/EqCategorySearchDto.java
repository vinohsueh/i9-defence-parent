package i9.defence.platform.dao.vo;

import java.io.Serializable;

public class EqCategorySearchDto extends PageListDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1166000316912439837L;
	//数据库ORDER BY 排序
	private String orderByClause;
	
	private String eqCategoryName;

	/**
	 * @return the orderByClause
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * @param orderByClause the orderByClause to set
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * @return the eqCategoryName
	 */
	public String getEqCategoryName() {
		return eqCategoryName;
	}

	/**
	 * @param eqCategoryName the eqCategoryName to set
	 */
	public void setEqCategoryName(String eqCategoryName) {
		this.eqCategoryName = eqCategoryName;
	}
}

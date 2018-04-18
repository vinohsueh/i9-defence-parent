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
	
	private String eqCategoryId;
	
	private int num;
	
	/*
	 * 项目负责人
	 */
	private Integer prijrctManagerId;
	/*
	 * 经销商
	 */
	private Integer distributorId;

	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Integer getPrijrctManagerId() {
		return prijrctManagerId;
	}

	public void setPrijrctManagerId(Integer prijrctManagerId) {
		this.prijrctManagerId = prijrctManagerId;
	}

	public Integer getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(Integer distributorId) {
		this.distributorId = distributorId;
	}

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

	public String getEqCategoryId() {
		return eqCategoryId;
	}

	public void setEqCategoryId(String eqCategoryId) {
		this.eqCategoryId = eqCategoryId;
	}
	
}

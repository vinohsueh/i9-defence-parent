package i9.defence.platform.dao.vo;

import java.io.Serializable;

public class ProjectSearchDto extends PageListDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1166000316912439837L;
	//数据库ORDER BY 排序
	private String orderByClause;	
    //项目名称
    private String projectName;
    //项目地址
    private String projectAddress;
	//经销商Id
	private Integer distributorId;
	//项目管理人员ID
	private Integer projectManagerId;
	 //项目所在省
    private String projectProvince;
    //项目所在市
    private String projectCity;
    //项目所在县/区
    private String projectCounty;

	public Integer getProjectManagerId() {
		return projectManagerId;
	}

	public void setProjectManagerId(Integer projectManagerId) {
		this.projectManagerId = projectManagerId;
	}

	public Integer getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(Integer distributorId) {
		this.distributorId = distributorId;
	}
	public String getOrderByClause() {
		return orderByClause;
	}
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectAddress() {
		return projectAddress;
	}
	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getProjectProvince() {
		return projectProvince;
	}

	public void setProjectProvince(String projectProvince) {
		this.projectProvince = projectProvince;
	}

	public String getProjectCity() {
		return projectCity;
	}

	public void setProjectCity(String projectCity) {
		this.projectCity = projectCity;
	}

	public String getProjectCounty() {
		return projectCounty;
	}

	public void setProjectCounty(String projectCounty) {
		this.projectCounty = projectCounty;
	}
	
}

package i9.defence.platform.dao.vo;

/** 
* @author user: jiace
* @version creatTime：2018年4月4日 下午4:50:15 
* 
*/
public class HiddenDangerSearchDto extends PageListDto{

	//数据库ORDER BY 排序
    private String orderByClause;
	/*
	 * 项目名称
	 */
	private String projectName;
	/*
	 * 项目所在省
	 */
	private String projectProvince;
	/*
	 * 项目所在市 
	 */
	private String projectCity;
	/*
	 * 项目所在县/区  
	 */
	private String projectCounty;
	/*
	 * 设备类型   
	 */
	private String eqCategoryName;
	
	
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
	public String getEqCategoryName() {
		return eqCategoryName;
	}
	public void setEqCategoryName(String eqCategoryName) {
		this.eqCategoryName = eqCategoryName;
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
}
 
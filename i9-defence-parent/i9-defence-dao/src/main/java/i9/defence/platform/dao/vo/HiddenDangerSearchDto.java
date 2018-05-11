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
	
	private Integer projectId;
	
	
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
	/*
	 * 隐患不为0
	 */
	private String hidden;
	/*
	 * 报警不为0
	 */
	private String danger;
	/*
	 * 项目负责人
	 */
	private Integer prijrctManagerId;
	/*
	 * 经销商
	 */
	private Integer distributorId;
	
	/**
	 * 设备类型
	 */ 
	private Integer equipmentCategoryId;
	/*
	 * 隐患大于0
	 */
	private Integer hiddenCount1;
	
	private String systemId;
	
	
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public Integer getHiddenCount1() {
		return hiddenCount1;
	}
	public void setHiddenCount1(Integer hiddenCount1) {
		this.hiddenCount1 = hiddenCount1;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getEquipmentCategoryId() {
		return equipmentCategoryId;
	}
	public void setEquipmentCategoryId(Integer equipmentCategoryId) {
		this.equipmentCategoryId = equipmentCategoryId;
	}
	public Integer getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(Integer distributorId) {
		this.distributorId = distributorId;
	}
	public Integer getPrijrctManagerId() {
		return prijrctManagerId;
	}
	public void setPrijrctManagerId(Integer prijrctManagerId) {
		this.prijrctManagerId = prijrctManagerId;
	}
	public String getHidden() {
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public String getDanger() {
		return danger;
	}
	public void setDanger(String danger) {
		this.danger = danger;
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
 
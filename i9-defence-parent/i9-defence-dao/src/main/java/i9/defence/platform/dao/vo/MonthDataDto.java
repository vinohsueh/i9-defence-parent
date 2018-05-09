package i9.defence.platform.dao.vo;
/** 
* 创建时间：2018年4月14日 上午10:51:55
* @author  lby
* @version  
* 
*/
public class MonthDataDto {
	
	private Integer projectId;
	
	private String startTime;
	
	private String endTime;
	
	//项目所在省
    private String projectProvince;
    //项目所在市
    private String projectCity;
    //项目所在县/区
    private String projectCounty;
    
    private Integer projectManagerId;
    
    private Integer distributorId;
    
    
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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

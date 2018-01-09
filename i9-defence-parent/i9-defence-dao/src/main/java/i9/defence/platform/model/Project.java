package i9.defence.platform.model;

import java.util.Date;
/**
 * 项目实体类
 * @author 姜哲
 * @create 2018年1月8日
 */
public class Project {
	//项目主键
    private Integer id;
    //项目名称
    private String projectName;
    //项目地址
    private String projectAddress;
    //项目坐标-经度
    private Double projectLongitude;
    //项目坐标-纬度
    private Double projectLatitude;
    //项目创建日期
    private Date projectDate;
    //建筑面积
    private Integer projectArea;
    //项目负责人Id
    private Integer dutyManId;
    //经销商Id
    private Integer distributorId;
    //项目安全责任人Id
    private Integer safetyManId;
    //备注
    private String remarks;
    //开关 0-关，1-开
    private Integer projectState;
    //项目负责人(一对一关系)
    private Manager dutyMan;
    //经销商(一对一关系)
    private Manager distributor;
    //项目安全责任人(一对一关系)
    private Manager safetyMan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress == null ? null : projectAddress.trim();
    }

    public Double getProjectLongitude() {
        return projectLongitude;
    }

    public void setProjectLongitude(Double projectLongitude) {
        this.projectLongitude = projectLongitude;
    }

    public Double getProjectLatitude() {
        return projectLatitude;
    }

    public void setProjectLatitude(Double projectLatitude) {
        this.projectLatitude = projectLatitude;
    }

    public Date getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(Date projectDate) {
        this.projectDate = projectDate;
    }

    public Integer getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(Integer projectArea) {
        this.projectArea = projectArea;
    }

    public Integer getDutyManId() {
        return dutyManId;
    }

    public void setDutyManId(Integer dutyManId) {
        this.dutyManId = dutyManId;
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public Integer getSafetyManId() {
        return safetyManId;
    }

    public void setSafetyManId(Integer safetyManId) {
        this.safetyManId = safetyManId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getProjectState() {
        return projectState;
    }

    public void setProjectState(Integer projectState) {
        this.projectState = projectState;
    }

	public Manager getDutyMan() {
		return dutyMan;
	}

	public void setDutyMan(Manager dutyMan) {
		this.dutyMan = dutyMan;
	}

	public Manager getDistributor() {
		return distributor;
	}

	public void setDistributor(Manager distributor) {
		this.distributor = distributor;
	}

	public Manager getSafetyMan() {
		return safetyMan;
	}

	public void setSafetyMan(Manager safetyMan) {
		this.safetyMan = safetyMan;
	}
    
}
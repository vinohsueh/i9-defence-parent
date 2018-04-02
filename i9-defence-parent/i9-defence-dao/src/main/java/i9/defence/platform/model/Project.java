package i9.defence.platform.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import i9.defence.platform.utils.StringUtil;
/**
 * 项目实体类
 * @author 姜哲
 * @create 2018年1月8日
 */
public class Project {
	//项目主键
    private Integer id;
    //项目名称
    @NotBlank(message="项目名称不能为空")
    private String projectName;
    //项目所在省
    private String projectProvince;
    //项目所在市
    private String projectCity;
    //项目所在县/区
    private String projectCounty;
    //项目所在详细地址
    @NotBlank(message="项目地址不能为空")
    private String projectAddress;
    //项目坐标-经度
    @NotNull(message="项目坐标-经度不能为空")
    private Double projectLongitude;
    //项目坐标-纬度
    @NotNull(message="项目坐标-纬度不能为空")
    private Double projectLatitude;
    //项目创建日期
    //@NotNull(message="项目创建日期不能为空")
    private Date projectDate;
    //建筑面积
    @NotNull(message="建筑面积不能为空")
    private Integer projectArea;
    //经销商Id
    @NotNull(message="经销商Id不能为空")
    private Integer distributorId;
    //备注
    @NotBlank(message="备注不能为空")
    private String remarks;
    //开关 0-关，1-开
    //@NotNull(message="开关 0-关，1-开不能为空")
    private Integer projectState;
    //经销商(一对一关系)
    private Manager distributor;
    //Apply表中项目个数
    private Integer delCount;

    public Integer getDelCount() {
		return delCount;
	}

	public void setDelCount(Integer delCount) {
		this.delCount = delCount;
	}

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
    
    public String getProjectDateStr() {
    	if(projectDate != null) {
    		return StringUtil.dateToString(projectDate);
    	}
    	return "";
    }

    public Integer getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(Integer projectArea) {
        this.projectArea = projectArea;
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
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

	public Manager getDistributor() {
		return distributor;
	}

	public void setDistributor(Manager distributor) {
		this.distributor = distributor;
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
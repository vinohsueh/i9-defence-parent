package i9.defence.platform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    //@NotNull(message="经销商Id不能为空")
    private Integer distributorId;
    //备注
    @NotBlank(message="备注不能为空")
    private String remarks;
    //开关 0-关，1-开
    //@NotNull(message="开关 0-关，1-开 不能为空")
    private Integer projectState;
    //经销商(一对一关系)
    private Manager distributor;
    //Apply表中项目个数
    private Integer delCount;
    //项目负责人 一对多
    private List<Client> clientList;
  //接收前台传参  项目负责人人IDS们
    private List<Integer> clientIds;
    //安全责任人 一对多
    private List<Manager> safeList;
    //接收前台传参  项目安全责任人IDS们
    private List<Integer> safeIds;

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
	public String getProjectAddressStr() {
		StringBuffer projectAddressStr = new StringBuffer();
		if(projectProvince !=null && !projectProvince.equals("")) {
			projectAddressStr.append(projectProvince);
			if(projectCity !=null && !projectCity.equals("")) {
				projectAddressStr.append(projectCity);
				if(projectCounty !=null && !projectCounty.equals("")) {
					projectAddressStr.append(projectCounty);
					if(projectAddress !=null && !projectAddress.equals("")) {
						projectAddressStr.append(projectAddress);
					}
				}
			}
		}
		if(projectAddressStr.length()>0) {
			return projectAddressStr.toString();
		}
		return "暂无";
	}

	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}
	
	public String getClientListStr() {
		if(clientList.size()>0) {
			StringBuffer clientListStr = new StringBuffer();
			Integer num = clientList.size();
			for(Client client:clientList) {
				if(num-1==0) {
					clientListStr.append(client.getName());
				}else {
					clientListStr.append(client.getName()+",");
				}
				num--;
			}
			return clientListStr.toString();
		}
		return "暂无";
	}
//这个是   后台操作的参数   配合set使用  与前台传参有关
	public List<Integer> getClientIds2() {
		return clientIds;
	}
//这个是  初始化 传到前端 的参数   用来展示默认选中的
	public List<Integer> getClientIds() {
		List<Integer> cList = new ArrayList<>();
		if(clientList.size()>0) {
			for(Client client:clientList) {
				cList.add(client.getId());
			}
			return cList;
		}
		return cList;
	}

	public void setClientIds(List<Integer> clientIds) {
		this.clientIds = clientIds;
	}

	public List<Manager> getSafeList() {
		return safeList;
	}

	public void setSafeList(List<Manager> safeList) {
		this.safeList = safeList;
	}
	
	public String getSafeListStr() {
		if(safeList.size()>0) {
			StringBuffer safeListStr = new StringBuffer();
			Integer num = safeList.size();
			for(Manager manager:safeList) {	
				if(num-1==0) {
					safeListStr.append(manager.getName());
				}else {
					safeListStr.append(manager.getName()+",");
				}
				num--;
			}
			return safeListStr.toString();
		}
		return "暂无";
	}
	
	public List<Integer> getSafeIds2() {
		return safeIds;
	}

	public List<Integer> getSafeIds() {
		List<Integer> sList = new ArrayList<>();
		if(safeList.size()>0) {
			for(Manager manager:safeList) {
				sList.add(manager.getId());
			}
			return sList;
		}
		return sList;
	}

	public void setSafeIds(List<Integer> safeIds) {
		this.safeIds = safeIds;
	}
    
	
}
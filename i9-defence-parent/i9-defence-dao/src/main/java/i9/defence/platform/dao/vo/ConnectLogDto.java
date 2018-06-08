package i9.defence.platform.dao.vo;

import java.util.Date;

import i9.defence.platform.utils.StringUtil;

public class ConnectLogDto extends PageListDto{
	
	private String projectName;
	
	private String eqCategoryName;
	
	private String equipmentPosition;
	
	private String equipmentRemarks;
	
	private Integer status; 
	
	private Date createTime;
	
	private String orderByClause;
	
	private String statusStr;
	
	private Integer managerId;
	
	private Integer managerId2;

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

	public String getEqCategoryName() {
		return eqCategoryName;
	}

	public void setEqCategoryName(String eqCategoryName) {
		this.eqCategoryName = eqCategoryName;
	}

	public String getEquipmentPosition() {
		return equipmentPosition;
	}

	public void setEquipmentPosition(String equipmentPosition) {
		this.equipmentPosition = equipmentPosition;
	}

	public String getEquipmentRemarks() {
		return equipmentRemarks;
	}

	public void setEquipmentRemarks(String equipmentRemarks) {
		this.equipmentRemarks = equipmentRemarks;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
   public String getCreateTimeStr() {
	    	if(createTime!=null) {
	    		return StringUtil.dateToString(createTime);
	    	}
	        return "";
	    }
   
   public String getStatusStr() {
   	if(status == 0) {
			this.statusStr = "掉线";
		}else if (status == 1) {
			this.statusStr = "登录";}
   	
       return statusStr;
   }

public Integer getManagerId() {
	return managerId;
}


public void setManagerId(Integer managerId) {
	this.managerId = managerId;
}

public Integer getManagerId2() {
	return managerId2;
}

public void setManagerId2(Integer managerId2) {
	this.managerId2 = managerId2;
}
   
}

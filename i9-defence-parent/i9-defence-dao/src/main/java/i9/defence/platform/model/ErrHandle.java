package i9.defence.platform.model;

import java.util.Date;

import i9.defence.platform.utils.StringUtil;

public class ErrHandle {
    private Integer id;

    private String handleCon;

    private Date handleDate;

   //处理状态(0为处理)(1已处理)
    private Integer handleState;

    private String eqDeviceId;

    private String eqAddRess;
    
    //(处理记录的故障类型类型)
    private Integer type;
    
    //处理记录的故障类型类型 前台获取
    private String typeStr;
    
    private String handleStateStr;
    
    private Integer handleManagerId;
    
    //处理人  一对一
    private Manager handleManager;
    
    private String eqCategoryName;
    
    private String projectName;
    
    private String equipmentRemarks;
    
    
    
    public String getEqCategoryName() {
		return eqCategoryName;
	}

	public void setEqCategoryName(String eqCategoryName) {
		this.eqCategoryName = eqCategoryName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getEquipmentRemarks() {
		return equipmentRemarks;
	}

	public void setEquipmentRemarks(String equipmentRemarks) {
		this.equipmentRemarks = equipmentRemarks;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHandleCon() {
        return handleCon;
    }

    public void setHandleCon(String handleCon) {
        this.handleCon = handleCon == null ? null : handleCon.trim();
    }

    public Integer getHandleManagerId() {
        return handleManagerId;
    }

    public void setHandleManagerId(Integer handleManagerId) {
        this.handleManagerId = handleManagerId;
    }

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }
    
    public String getHandleDateStr() {
    	if(handleDate!=null) {
    		return StringUtil.dateToString(handleDate);
    	}
        return "";
    }

    public Integer getHandleState() {
        return handleState;
    }

    public void setHandleState(Integer handleState) {
        this.handleState = handleState;
    }
    
    public String getHandleStateStr() {
    	if(handleState == 0) {
			this.handleStateStr = "待处理";
		}else if (handleState == 1) {
			this.handleStateStr = "已处理";}
    	
        return handleStateStr;
    }

    public String getEqDeviceId() {
        return eqDeviceId;
    }

    public void setEqDeviceId(String eqDeviceId) {
        this.eqDeviceId = eqDeviceId == null ? null : eqDeviceId.trim();
    }

    public String getEqAddRess() {
        return eqAddRess;
    }

    public void setEqAddRess(String eqAddRess) {
        this.eqAddRess = eqAddRess == null ? null : eqAddRess.trim();
    }

	public Manager getHandleManager() {
		return handleManager;
	}

	public void setHandleManager(Manager handleManager) {
		this.handleManager = handleManager;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeStr() {
		if (type == 1) {
			this.typeStr = "报警";
		}else {
			this.typeStr = "隐患";
		}
		return typeStr;
	}

	

}
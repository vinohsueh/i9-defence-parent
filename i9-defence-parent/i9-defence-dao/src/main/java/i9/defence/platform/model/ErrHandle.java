package i9.defence.platform.model;

import java.util.Date;
import java.util.List;

import i9.defence.platform.dao.vo.EqChannelDataDto;
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
    
    private String equipmentPosition;
    
    //处理人  一对一
    private Manager handleManager;
    
    private String eqCategoryName;
    
    private String projectName;
    
    private String equipmentRemarks;
    
    /**
     * 设备Id
     */
    private Integer equipmentId;
    
    private List<EqChannelDataDto> eqChannelDataList;
    
    public String getEquipmentPosition() {
		return equipmentPosition;
	}

	public void setEquipmentPosition(String equipmentPosition) {
		this.equipmentPosition = equipmentPosition;
	}

	public List<EqChannelDataDto> getEqChannelDataList() {
		return eqChannelDataList;
	}

	public void setEqChannelDataList(List<EqChannelDataDto> eqChannelDataList) {
		this.eqChannelDataList = eqChannelDataList;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

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
		if (2 ==type) {
			this.typeStr = "报警";
		}else if(3 ==type) {
			this.typeStr = "隐患";
		}else if(1 ==type){
			this.typeStr = "离线";
		}else {
			this.typeStr = "正常";
		}
		return typeStr;
	}

	

}
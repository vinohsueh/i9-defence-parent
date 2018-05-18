package i9.defence.platform.model;

import java.util.Date;

import i9.defence.platform.utils.StringUtil;

public class ChannelData {
    private Integer id;

    private String systemId;

    private Integer channel;

    private Date dateTime;

    private Integer type;

    private String value;
    
    private String systemType;
    
    private Integer dealStatus;

	private String dealDetail;
	
	private String deviceId;
	
	private String deviceAddress;
	
	private String errorName;
	
	private Date dealDate;
	
	private Integer dealManagerId;
	
	/**
	 * 设备一级分类类型id
	 */
	private Integer equipmentCatId;
	//一对一 处理人信息
	private Manager dealManager;
	
	
    public Integer getEquipmentCatId() {
		return equipmentCatId;
	}

	public void setEquipmentCatId(Integer equipmentCatId) {
		this.equipmentCatId = equipmentCatId;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getDeviceAddress() {
		return deviceAddress;
	}

	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String getDealDetail() {
		return dealDetail;
	}

	public void setDealDetail(String dealDetail) {
		this.dealDetail = dealDetail;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
    
    public String getDateTimeStr(){
		if (dateTime != null) {
			return StringUtil.dateToString(dateTime);
		}
		return "";
    }
    
	public void calDeviceId(String loop) {
		StringBuffer str = new StringBuffer();
		str.append(this.systemId).append(loop).append(this.deviceAddress);
		this.deviceId = str.toString();
	}

	public Date getDealDate() {
		return dealDate;
	}

	public String getDealDateStr() {
		if(dealDate != null) {
			return StringUtil.dateToString(dealDate);
		}
		return "";
	}
	
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public Integer getDealManagerId() {
		return dealManagerId;
	}

	public void setDealManagerId(Integer dealManagerId) {
		this.dealManagerId = dealManagerId;
	}

	public Manager getDealManager() {
		return dealManager;
	}

	public void setDealManager(Manager dealManager) {
		this.dealManager = dealManager;
	}

	
}
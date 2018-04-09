package i9.defence.platform.model;

import java.util.Date;

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
}
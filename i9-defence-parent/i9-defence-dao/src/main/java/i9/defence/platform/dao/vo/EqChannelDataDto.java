package i9.defence.platform.dao.vo;

import java.io.Serializable;

import i9.defence.platform.cache.ErrorTypeCache;

public class EqChannelDataDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8300075060522393014L;

	private String channelName;
	
	private Integer channelNum;
	
	private String channelValue;
	
	private Integer type;
	
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getChannelName() {
		if(channelName == null) {
			return "通道"+channelNum;
		}
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(Integer channelNum) {
		this.channelNum = channelNum;
	}

	public String getChannelValue() {
		if (type == 0) {
			return ErrorTypeCache.getCacheDict(channelValue);
		}else{
			return channelValue;
		}
	}

	public void setChannelValue(String channelValue) {
		this.channelValue = channelValue;
	}
	
}

package i9.defence.platform.dao.vo;

import java.util.Date;
import java.util.List;

import i9.defence.platform.cache.ErrorTypeCache;
import i9.defence.platform.utils.StringUtil;

/**
 * @author user: gbq
 * @version creatTime：2018年4月9日 上午10:50:36
 * 
 */
public class HiddenDangerChannelDto {
	/*
	 * 对应channel id
	 */
	private Integer id;

	/*
	 * 设备编号
	 */
	private String systemId;
	/*
	 * 通道名称
	 */
	private String name;
	/*
	 * 阀值
	 */
	private String value;
	/*
	 * 时间
	 */
	private Date dateTime;
	/*
	 * 通道
	 */
	private Integer channel;
	/*
	 * 处理表示1未处理2标记错误3标记故障
	 */
	private Integer dealStatus;
	/*
	 * 处理详情
	 */
	private String dealDetail;
	
	private Integer type;
	
	private Integer equipmentCategoryId;
	
	private String channelName;
	
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getEquipmentCategoryId() {
		return equipmentCategoryId;
	}

	public void setEquipmentCategoryId(Integer equipmentCategoryId) {
		this.equipmentCategoryId = equipmentCategoryId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
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
		this.systemId = systemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getDateTimeStr() {
		if (dateTime != null) {
			return StringUtil.dateToString(dateTime);
		}
		return "";
	}
	
	public String getChannelValue() {
        if (type == 0) {
        	if (ErrorTypeCache.getCacheJhType(value+equipmentCategoryId) == 1) {
        		String faultNames = ErrorTypeCache.getCacheDict(value+equipmentCategoryId);
        		String [] faultNameArray = faultNames.split("/");
        		if (faultNameArray.length < 2 ){
        			return "未知故障(格式定义错误)";
        		}
        		List<Integer> fault1Channels = ErrorTypeCache.getCacheFault1Channels(value+equipmentCategoryId);
        		if (fault1Channels != null && fault1Channels.size() != 0) {
        			if (fault1Channels.contains(channel)){
            			return faultNameArray[0];
            		} 
        		}
        		
        		List<Integer> fault2Channels = ErrorTypeCache.getCacheFault2Channels(value+equipmentCategoryId);
        		if (fault2Channels != null && fault2Channels.size() != 0) {
        			if (fault2Channels.contains(channel)){
            			return faultNameArray[1];
            		} 
        		}
        		
    			return "未定义该通道故障名称";
        	
        	}else{
        		return ErrorTypeCache.getCacheDict(value+equipmentCategoryId);
        	}
        } else {
            return value;
        }
    }
}

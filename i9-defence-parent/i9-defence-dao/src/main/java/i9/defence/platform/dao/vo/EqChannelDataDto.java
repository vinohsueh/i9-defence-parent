package i9.defence.platform.dao.vo;

import java.io.Serializable;
import java.util.List;

import i9.defence.platform.cache.ErrorTypeCache;

public class EqChannelDataDto implements Serializable {

    private static final long serialVersionUID = 8300075060522393014L;

    private String channelName;

    private Integer channelNum;

    private String channelValue;

    private Integer type;

    private Integer equipmentCategoryId;
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getChannelName() {
        if (channelName == null) {
            return "通道" + channelNum;
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
        	if (ErrorTypeCache.getCacheJhType(channelValue+equipmentCategoryId) == 1) {
        		String faultNames = ErrorTypeCache.getCacheDict(channelValue+equipmentCategoryId);
        		String [] faultNameArray = faultNames.split("/");
        		if (faultNameArray.length < 2 ){
        			return "未知故障(格式定义错误)";
        		}
        		List<Integer> fault1Channels = ErrorTypeCache.getCacheFault1Channels(channelValue+equipmentCategoryId);
        		if (fault1Channels != null && fault1Channels.size() != 0) {
        			if (fault1Channels.contains(channelNum)){
            			return faultNameArray[0];
            		} 
        		}
        		
        		List<Integer> fault2Channels = ErrorTypeCache.getCacheFault2Channels(channelValue+equipmentCategoryId);
        		if (fault2Channels != null && fault2Channels.size() != 0) {
        			if (fault2Channels.contains(channelNum)){
            			return faultNameArray[1];
            		} 
        		}
        		
    			return "未定义该通道故障名称";
        	
        	}else{
        		return ErrorTypeCache.getCacheDict(channelValue+equipmentCategoryId);
        	}
        } else {
            return channelValue;
        }
    }
    
    public Integer getEquipmentCategoryId() {
		return equipmentCategoryId;
	}

	public void setEquipmentCategoryId(Integer equipmentCategoryId) {
		this.equipmentCategoryId = equipmentCategoryId;
	}

	public void setChannelValue(String channelValue) {
        this.channelValue = channelValue;
    }
}

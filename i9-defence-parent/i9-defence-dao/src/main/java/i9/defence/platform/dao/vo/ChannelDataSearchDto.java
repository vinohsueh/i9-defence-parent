package i9.defence.platform.dao.vo;

import java.util.Date;
import java.util.List;

import i9.defence.platform.utils.StringUtil;

/** 
* 创建时间：2018年3月27日 下午2:48:52
* @author  lby
* @version  
* 
*/
public class ChannelDataSearchDto  extends PageListDto{
	
	private int equipmentId;
	
	private String deviceId;

    private Integer channel;
    
    private String orderByClause;
    
    private List<Integer> types;
    
    private String startDateString;
    
    private String endDateString;


	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public List<Integer> getTypes() {
		return types;
	}

	public void setTypes(List<Integer> types) {
		this.types = types;
	}

	public Date getStartDate() {
		return StringUtil.stringToDateByRep(startDateString,"yyyy/MM/dd");
	}

	public Date getEndDate() {
		return StringUtil.stringToDateByRep(endDateString,"yyyy/MM/dd");
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}


	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
    
    
}

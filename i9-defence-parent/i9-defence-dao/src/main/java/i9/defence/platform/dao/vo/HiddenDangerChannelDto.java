package i9.defence.platform.dao.vo;

import java.util.Date;

import i9.defence.platform.utils.StringUtil;

/** 
* @author user: gbq
* @version creatTime：2018年4月9日 上午10:50:36 
* 
*/
public class HiddenDangerChannelDto {
	/*
	 * 对应channel  id
	 * */
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
	    	if(dateTime!=null) {
	    		return StringUtil.dateToString(dateTime);
	    	}
	        return "";
	    }
}

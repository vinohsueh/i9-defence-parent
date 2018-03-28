package i9.defence.platform.dao.vo;
/** 
* 创建时间：2018年3月27日 下午2:48:52
* @author  lby
* @version  
* 
*/
public class ChannelDataSearchDto {
	
	private String systemId;

    private Integer channel;
    
    private String orderByClause;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
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

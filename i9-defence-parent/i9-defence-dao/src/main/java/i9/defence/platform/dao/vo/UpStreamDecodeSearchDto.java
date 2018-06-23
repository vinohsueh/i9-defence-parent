package i9.defence.platform.dao.vo;

import java.util.Date;

/** 
* @author user: jiace
* @version creatTime：2018年3月19日 上午11:03:13 
* 
*/
public class UpStreamDecodeSearchDto extends PageListDto{

	private String orderByClause;
	
	private Date submitDate;
	
    private String hexStr;
    
    private String channelId;
    
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getHexStr() {
		return hexStr;
	}

	public void setHexStr(String hexStr) {
		this.hexStr = hexStr;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	
	
}
 
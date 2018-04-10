package i9.defence.platform.dao.vo;

import java.util.List;

/** 
* @author user: gbq
* @version creatTime：2018年4月10日 上午10:50:36 
* 
*/
public class DealStatusDto {
	private List<Integer> ids;
	private Integer dealStatus;
	
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public Integer getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}
}

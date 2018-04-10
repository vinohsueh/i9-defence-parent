package i9.defence.platform.dao.vo;

import java.util.List;

/** 
* @author user: gbq
* @version creatTime：2018年4月10日 上午10:50:36 
* 
*/
public class DealStatusDto {
	private List<Integer> id;
	private Integer dealStatus;
	
	public List<Integer> getId() {
		return id;
	}
	public void setId(List<Integer> id) {
		this.id = id;
	}
	public Integer getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}
}

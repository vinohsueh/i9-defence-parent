package i9.defence.platform.dao.vo;

import java.util.List;

/** 
* @author user: gbq
* @version creatTime：2018年4月10日 上午10:50:36 
* 
*/
public class DealStatusDto {
	/*
	 * id集合
	 */
	private List<Integer> ids;
	/*
	 * 标志
	 */
	private Integer dealStatus;
	/*
	 * 处理详情
	 */
	private List<String> dealDetail;
	
	public List<String> getDealDetail() {
		return dealDetail;
	}
	public void setDealDetail(List<String> dealDetail) {
		this.dealDetail = dealDetail;
	}
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

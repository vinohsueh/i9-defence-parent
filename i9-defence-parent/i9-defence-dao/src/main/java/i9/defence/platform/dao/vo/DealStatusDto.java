package i9.defence.platform.dao.vo;

/** 
* @author user: gbq
* @version creatTime：2018年4月10日 上午10:50:36 
* 
*/
public class DealStatusDto {
	/*
	 * id集合
	 */
	private Integer id;
	/*
	 * 标志
	 */
	private Integer dealStatus;
	/*
	 * 处理详情
	 */
	private String dealDetail;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	
}

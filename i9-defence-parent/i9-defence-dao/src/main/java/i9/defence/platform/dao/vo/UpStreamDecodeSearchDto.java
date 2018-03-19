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
 
package i9.defence.platform.dao.vo;
/** 
* 创建时间：2018年5月8日 下午2:51:50
* @author  lby
* @version  
* 
*/
public class TotalEquipmentDto {
	
	/**
	 * 总数
	 */
	private Integer total;
	
	/**
	 * 故障
	 */
	private Integer fault;
	
	/**
	 * 报警
	 */
	private Integer alert;
		
	/**
	 * 离线
	 */
	private Integer offline;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getFault() {
		return fault;
	}

	public void setFault(Integer fault) {
		this.fault = fault;
	}

	public Integer getAlert() {
		return alert;
	}

	public void setAlert(Integer alert) {
		this.alert = alert;
	}

	public Integer getOffline() {
		return offline;
	}

	public void setOffline(Integer offline) {
		this.offline = offline;
	}
	
	
}

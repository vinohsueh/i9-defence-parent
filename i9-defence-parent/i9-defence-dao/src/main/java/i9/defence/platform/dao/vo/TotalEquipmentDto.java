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
	private int total;
	
	/**
	 * 隐患
	 */
	private int fault;
	
	/**
	 * 报警
	 */
	private int alert;
		
	/**
	 * 离线
	 */
	private int offline;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getFault() {
		return fault;
	}

	public void setFault(int fault) {
		this.fault = fault;
	}

	public int getAlert() {
		return alert;
	}

	public void setAlert(int alert) {
		this.alert = alert;
	}

	public int getOffline() {
		return offline;
	}

	public void setOffline(int offline) {
		this.offline = offline;
	}
	
}

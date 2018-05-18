package i9.defence.platform.dao.vo;

import java.io.Serializable;

public class ErrHandleSearchDto extends PageListDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1202534335682812606L;
	
	private String orderByClause;
	
	private String eqDeviceId;
	
	private Integer handleState;
	
	private Integer managerId;

	public String getOrderByClause() {
		return orderByClause;
	}

	public Integer getHandleState() {
		return handleState;
	}

	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getEqDeviceId() {
		return eqDeviceId;
	}

	public void setEqDeviceId(String eqDeviceId) {
		this.eqDeviceId = eqDeviceId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}	

	
}

package i9.defence.platform.dao.vo;

import java.io.Serializable;
import java.util.List;

public class ErrHandleUnifiedDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1254227924831565077L;

	//被处理的设备ID
	private Integer eqId;
	
	//处理内容
	private String handleCon;
	
	private Integer handleState;
	
	//处理设备 故障类型
	private Integer eqType;
	
	/**
	 * 被处理的设备Ids
	 */
	private List<Integer> eqIds;
	
	public List<Integer> getEqIds() {
		return eqIds;
	}

	public void setEqIds(List<Integer> eqIds) {
		this.eqIds = eqIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getHandleState() {
		return handleState;
	}

	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}

	public Integer getEqId() {
		return eqId;
	}

	public void setEqId(Integer eqId) {
		this.eqId = eqId;
	}

	public String getHandleCon() {
		return handleCon;
	}

	public void setHandleCon(String handleCon) {
		this.handleCon = handleCon;
	}

	public Integer getEqType() {
		return eqType;
	}

	public void setEqType(Integer eqType) {
		this.eqType = eqType;
	}
	
	

}

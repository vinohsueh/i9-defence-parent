package i9.defence.platform.dao.vo;

/**
 * 回路Dto
 * @ClassName: EquipmentLoopDto 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年5月25日 下午4:28:40
 */
public class EquipmentLoopDto extends PageListDto {
		
	 /**
     * 设备唯一标识
     */
    private String eqDeviceId;
    
    /**
     * 回路号
     */
    private Integer looId;
    
	//数据库ORDER BY 排序
	private String orderByClause;

	public String getEqDeviceId() {
		return eqDeviceId;
	}

	public void setEqDeviceId(String eqDeviceId) {
		this.eqDeviceId = eqDeviceId;
	}

	public Integer getLooId() {
		return looId;
	}

	public void setLooId(Integer looId) {
		this.looId = looId;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
    
	
}

package i9.defence.platform.model;

/**
 * 设备故障统计
* @ClassName: EquipmentFailure 
* @Description: TODO
* @author luobo
* @date 2018年1月8日 下午5:10:58 
*
 */
public class EquipmentFailure {
    private Integer id;
    
    /**
     * 处理人
     */
    private String conductor;
    
    /**
     * 处理状态（0：未处理,1：已处理)
     */
    private Integer dealState;
    
    /**
     * 设备监控
     */
    private EquipmentMonitor equipmentMonitor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor == null ? null : conductor.trim();
    }

    public Integer getDealState() {
        return dealState;
    }

    public void setDealState(Integer dealState) {
        this.dealState = dealState;
    }

	public EquipmentMonitor getEquipmentMonitor() {
		return equipmentMonitor;
	}

	public void setEquipmentMonitor(EquipmentMonitor equipmentMonitor) {
		this.equipmentMonitor = equipmentMonitor;
	}

 
}
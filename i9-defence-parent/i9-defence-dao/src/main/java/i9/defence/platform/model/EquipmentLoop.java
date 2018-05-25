package i9.defence.platform.model;

/**
 * 设备回路表
 * @ClassName: EquipmentLoop 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年5月25日 下午4:27:52
 */
public class EquipmentLoop {
    private Integer id;
    
    /**
     * 设备唯一标识
     */
    private String eqDeviceId;
    
    /**
     * 回路号
     */
    private Integer looId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEqDeviceId() {
        return eqDeviceId;
    }

    public void setEqDeviceId(String eqDeviceId) {
        this.eqDeviceId = eqDeviceId == null ? null : eqDeviceId.trim();
    }

    public Integer getLooId() {
        return looId;
    }

    public void setLooId(Integer looId) {
        this.looId = looId;
    }
}
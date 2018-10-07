package i9.defence.platform.dao.vo;

/**
 * 设备最新事件时间
 * @ClassName: EquipmentNewestDto 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年9月18日 下午11:41:55
 */
public class EquipmentNewestDto {
    
    /**
     * 唯一设备Id
     */
    private String eqDeviceId;
    
    /**
     * 最新事件时间
     */
    private String newEventTime;

    public String getEqDeviceId() {
        return eqDeviceId;
    }

    public void setEqDeviceId(String eqDeviceId) {
        this.eqDeviceId = eqDeviceId;
    }

    public String getNewEventTime() {
        return newEventTime;
    }

    public void setNewEventTime(String newEventTime) {
        this.newEventTime = newEventTime;
    }
    
}

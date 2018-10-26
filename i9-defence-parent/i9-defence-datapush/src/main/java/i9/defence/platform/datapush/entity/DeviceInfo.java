package i9.defence.platform.datapush.entity;

import i9.defence.platform.datapush.utils.PowerStateEnum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 设备信息
 * 
 * @author R12
 * @date 2018年10月22日 14:17:51
 */
@Entity
@Table(name = "tb_device_info")
public class DeviceInfo implements java.io.Serializable {

    @Transient
    public String getPowerStateDesc() {
        PowerStateEnum powerStateEnum = PowerStateEnum.valueOf(this.powerState);
        return powerStateEnum.getName();
    }

    @Transient
    public PowerStateEnum getPowerState0() {
        PowerStateEnum powerStateEnum = PowerStateEnum.valueOf(this.powerState);
        return powerStateEnum;
    }

    private static final long serialVersionUID = -9079076068871929718L;

    public DeviceInfo() {
    }

    /**
     * ID
     */
    private String id;

    /**
     * 设备编号
     */
    private String deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * imei码
     */
    private String imei;

    /**
     * 设备状态
     */
    private Integer powerState;

    /**
     * 创建日期
     */
    private Date createDate;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getPowerState() {
        return powerState;
    }

    public void setPowerState(Integer powerState) {
        this.powerState = powerState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

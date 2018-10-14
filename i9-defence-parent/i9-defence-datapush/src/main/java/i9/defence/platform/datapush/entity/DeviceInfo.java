package i9.defence.platform.datapush.entity;

import i9.defence.platform.datapush.utils.PowerStateEnum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "tb_device_info")
public class DeviceInfo implements java.io.Serializable {

    @Transient
    public String getPowerStateDesc() {
        PowerStateEnum powerStateEnum = PowerStateEnum.valueOf(this.powerState);
        return powerStateEnum.getName();
    }

    private static final long serialVersionUID = -9079076068871929718L;

    public DeviceInfo() {
    }

    private String id;

    private String deviceId;

    private String deviceName;

    private String imei;

    private Integer powerState;

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

package i9.defence.platform.datapush.dto;

import java.util.Date;

public class DeviceInfoDto implements java.io.Serializable {

    private static final long serialVersionUID = -2634267931495344908L;

    public DeviceInfoDto() {
    }

    public static DeviceInfoDto build(String id, String deviceId, String deviceName, 
            String imei, Integer powerState, Date createDate) {
        DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
        deviceInfoDto.setId(id);
        deviceInfoDto.setDeviceId(deviceId);
        deviceInfoDto.setDeviceName(deviceName);
        deviceInfoDto.setImei(imei);
        deviceInfoDto.setPowerState(powerState);
        deviceInfoDto.setCreateDate(createDate);
        return deviceInfoDto;
    }

    private String id;

    private String deviceId;

    private String deviceName;

    private String imei;

    private Integer powerState;

    private Date createDate;

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

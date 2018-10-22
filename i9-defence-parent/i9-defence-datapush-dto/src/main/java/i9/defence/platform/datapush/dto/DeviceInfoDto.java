package i9.defence.platform.datapush.dto;

import java.util.Date;

/**
 * 设备信息DTO
 * 
 * @author R12
 * @date 2018年10月22日 14:03:42
 */
public class DeviceInfoDto implements java.io.Serializable {

    private static final long serialVersionUID = -2634267931495344908L;

    public DeviceInfoDto() {
    }

    public static DeviceInfoDto build(String id, String deviceId, String deviceName, String imei, Integer powerState,
            Date createDate) {
        DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
        deviceInfoDto.setId(id);
        deviceInfoDto.setDeviceId(deviceId);
        deviceInfoDto.setDeviceName(deviceName);
        deviceInfoDto.setImei(imei);
        deviceInfoDto.setPowerState(powerState);
        deviceInfoDto.setCreateDate(createDate);
        return deviceInfoDto;
    }

    /**
     * 设备编号
     */
    private String id;

    /**
     * onenet设备编号
     */
    private String deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * imei值
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

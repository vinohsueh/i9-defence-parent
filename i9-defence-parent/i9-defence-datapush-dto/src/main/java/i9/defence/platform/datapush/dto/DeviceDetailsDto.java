package i9.defence.platform.datapush.dto;

import java.util.List;

/**
 * 设备详情DTO
 * 
 * @author R12
 * @date 2018年10月22日 14:02:52
 */
public class DeviceDetailsDto implements java.io.Serializable {

    private static final long serialVersionUID = 1988234770384332659L;

    /**
     * 设备信息
     */
    private DeviceInfoDto deviceInfoDto;

    /**
     * 设备属性列表
     */
    private List<DeviceAttributeDto> deviceAttributeDtos;

    public DeviceInfoDto getDeviceInfoDto() {
        return deviceInfoDto;
    }

    public void setDeviceInfoDto(DeviceInfoDto deviceInfoDto) {
        this.deviceInfoDto = deviceInfoDto;
    }

    public List<DeviceAttributeDto> getDeviceAttributeDtos() {
        return deviceAttributeDtos;
    }

    public void setDeviceAttributeDtos(List<DeviceAttributeDto> deviceAttributeDtos) {
        this.deviceAttributeDtos = deviceAttributeDtos;
    }
}

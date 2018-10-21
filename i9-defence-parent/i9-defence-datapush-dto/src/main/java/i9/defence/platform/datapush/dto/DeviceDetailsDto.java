package i9.defence.platform.datapush.dto;

import java.util.List;

public class DeviceDetailsDto implements java.io.Serializable {

    private static final long serialVersionUID = 1988234770384332659L;

    private DeviceInfoDto deviceInfoDto;
    
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

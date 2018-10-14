package i9.defence.platform.datapush.service;

import i9.defence.platform.datapush.entity.DeviceInfo;

import java.util.List;

public interface DeviceService {

    List<DeviceInfo> getDeviceInfoList();

    DeviceInfo getDeviceInfoById(String id);

    void deleteDevice(String deviceId);

    void saveDeviceInfo(DeviceInfo deviceInfo);
}

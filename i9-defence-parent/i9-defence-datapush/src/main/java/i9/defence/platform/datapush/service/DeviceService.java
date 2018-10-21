package i9.defence.platform.datapush.service;

import i9.defence.platform.datapush.entity.DeviceAttribute;
import i9.defence.platform.datapush.entity.DeviceInfo;

import java.util.HashMap;
import java.util.List;

public interface DeviceService {

    List<DeviceInfo> getDeviceInfoList();

    DeviceInfo getDeviceInfoById(String id);

    void deleteDevice(String deviceId);

    void saveDeviceInfo(DeviceInfo deviceInfo);

    HashMap<String, DeviceAttribute> getDeviceAttributeValueResult(String deviceId);

    void saveDeviceAttribute(DeviceAttribute deviceAttribute);

    void updateDeviceAttribute(DeviceAttribute deviceAttribute);

    void refreshDevice(String deviceId) throws Exception;

    void addDevice(String deviceId) throws Exception;

    List<DeviceInfo> getDeviceInfoListByIds(List<String> ids);
}

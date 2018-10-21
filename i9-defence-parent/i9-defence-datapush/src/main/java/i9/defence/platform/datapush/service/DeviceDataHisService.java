package i9.defence.platform.datapush.service;

import java.util.List;

import i9.defence.platform.datapush.entity.DeviceDataHis;

public interface DeviceDataHisService {

    List<DeviceDataHis> queryDeviceDataHisDto(String deviceId, String datastream, String startDate, String endDate);
}

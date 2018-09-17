package i9.defence.platform.microservice.mq.service.impl;

import i9.defence.platform.microservice.mq.service.DeviceRecordService;
import org.springframework.stereotype.Service;

@Service
public class DeviceRecordServiceImpl implements DeviceRecordService {

    @Override
    public void recordDeviceLastSubmitDate(String deviceId) {
        //TODO 在这里处理设备编号记录设备最后一次上行数据日期
    }
}

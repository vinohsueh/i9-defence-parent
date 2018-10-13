package i9.defence.platform.datapush.service.impl;

import javax.transaction.Transactional;

import i9.defence.platform.datapush.entity.DeviceInfo;
import i9.defence.platform.datapush.respository.DeviceInfoRepository;
import i9.defence.platform.datapush.service.ReceiveMessagePowerStateService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiveMessagePowerStateServiceImpl implements ReceiveMessagePowerStateService {

    @Transactional
    @Override
    public void dealWithUplinkData(JSONObject data) {
        int deviceId = data.getInt("dev_id");
        int status = data.getInt("status");
        DeviceInfo deviceInfo = this.deviceInfoRepository.selectDeviceInfoByDeviceId(String.valueOf(deviceId));
        if (deviceInfo == null) {
            return;
        }
        this.deviceInfoRepository.updateDeviceInfoPowerState(status, deviceInfo.getId());
    }

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;
}

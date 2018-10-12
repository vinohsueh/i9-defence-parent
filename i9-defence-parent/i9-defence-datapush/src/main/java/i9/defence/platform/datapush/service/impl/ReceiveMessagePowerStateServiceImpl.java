package i9.defence.platform.datapush.service.impl;

import i9.defence.platform.datapush.entity.DeviceInfo;
import i9.defence.platform.datapush.respository.DeviceInfoRepository;
import i9.defence.platform.datapush.service.ReceiveMessagePowerStateService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiveMessagePowerStateServiceImpl implements ReceiveMessagePowerStateService {

    @Override
    public void dealWithUplinkData(JSONObject data) {
        String deviceId = data.getString("dev_id");
        // long at = data.getLong("at");
        int status = data.getInt("status");
        DeviceInfo deviceInfo = this.deviceInfoRepository.selectDeviceInfoByDeviceId(deviceId);
        if (deviceInfo == null) {
            return;
        }
        this.deviceInfoRepository.updateDeviceInfoPowerState(status, deviceInfo.getId());
    }

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;
}

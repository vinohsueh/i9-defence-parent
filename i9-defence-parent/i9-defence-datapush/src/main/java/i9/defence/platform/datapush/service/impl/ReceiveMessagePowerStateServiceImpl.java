package i9.defence.platform.datapush.service.impl;

import i9.defence.platform.datapush.entity.DeviceInfo;
import i9.defence.platform.datapush.respository.DeviceInfoRepository;
import i9.defence.platform.datapush.service.ReceiveMessagePowerStateService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 设备状态消息处理服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:52:38
 */
@Service
public class ReceiveMessagePowerStateServiceImpl implements ReceiveMessagePowerStateService {

    /**
     * 处理上行数据消息
     * 
     * @param data
     */
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

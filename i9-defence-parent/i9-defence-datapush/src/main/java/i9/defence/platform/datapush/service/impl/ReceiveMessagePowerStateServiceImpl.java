package i9.defence.platform.datapush.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.datapush.service.DeviceService;
import i9.defence.platform.datapush.service.ReceiveMessagePowerStateService;
import i9.defence.platform.datapush.utils.PowerStateEnum;

/**
 * 设备状态消息处理服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:52:38
 */
@Service
public class ReceiveMessagePowerStateServiceImpl implements ReceiveMessagePowerStateService {

    @Autowired
    private DeviceService deviceService;

    /**
     * 处理上行数据消息
     * 
     * @param data
     */
    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    @Override
    public void dealWithUplinkData(JSONObject data) {
        int deviceId = data.getInt("dev_id");
        int status = data.getInt("status");
        PowerStateEnum powerStateEnum;
        if (status == 0) {
            powerStateEnum = PowerStateEnum.DEVICE_OFFLINE;
        } else {
            powerStateEnum = PowerStateEnum.DEVICE_ONLINE;
        }
        this.deviceService.refreshDeviceInfoPowerState(String.valueOf(deviceId), powerStateEnum);
    }
}

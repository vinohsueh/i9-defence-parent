package i9.defence.platform.datapush.service.impl;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.datapush.entity.DeviceAttribute;
import i9.defence.platform.datapush.entity.DeviceDataHis;
import i9.defence.platform.datapush.service.DeviceDataHisService;
import i9.defence.platform.datapush.service.DeviceService;
import i9.defence.platform.datapush.service.ReceiveMessageDataPointService;
import i9.defence.platform.datapush.utils.PowerStateEnum;
import i9.defence.platform.datapush.utils.StringHelper;

/**
 * 数据点消息处理服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:52:38
 */
@Service
public class ReceiveMessageDataPointServiceImpl implements ReceiveMessageDataPointService {

    @Autowired
    private DeviceService deviceService;
    
    @Autowired
    private DeviceDataHisService deviceDataHisService;

    /**
     * 处理上行数据消息
     * 
     * @param data
     */
    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    @Override
    public void dealWithUplinkData(JSONObject data) {
        int deviceId = data.getInt("dev_id");
        String datastream = data.getString("ds_id");
        long at = data.getLong("at");
        String value = String.valueOf(data.get("value"));

        DeviceDataHis deviceDataHis = new DeviceDataHis();
        deviceDataHis.setId(StringHelper.randomUUIDStr());
        deviceDataHis.setDeviceId(String.valueOf(deviceId));
        deviceDataHis.setDatastream(datastream);
        deviceDataHis.setValue(value);
        deviceDataHis.setCreateDate(new Date(at));
        this.deviceDataHisService.saveDeviceDataHis(deviceDataHis);

        DeviceAttribute deviceAttribute = this.deviceService.getAndCreateDeviceAttribute(String.valueOf(deviceId), datastream);
        this.deviceService.updateDeviceAttributeLastValue(value, new Date(at), deviceAttribute.getId());

        if ("3_0_21".equals(datastream)) {
            if ("1".equals(value)) {
                PowerStateEnum powerStateEnum = PowerStateEnum.ERROR;
                this.deviceService.refreshDeviceInfoPowerState(String.valueOf(deviceId), powerStateEnum);
            }
            if ("4".equals(value)) {
                PowerStateEnum powerStateEnum = PowerStateEnum.BATTERY_LOW;
                this.deviceService.refreshDeviceInfoPowerState(String.valueOf(deviceId), powerStateEnum);
            }
        }
    }
}

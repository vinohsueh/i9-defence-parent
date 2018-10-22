package i9.defence.platform.datapush.service.impl;

import i9.defence.platform.datapush.entity.DeviceAttribute;
import i9.defence.platform.datapush.entity.DeviceDataHis;
import i9.defence.platform.datapush.respository.DeviceAttributeRepository;
import i9.defence.platform.datapush.respository.DeviceDataHisRepository;
import i9.defence.platform.datapush.service.ReceiveMessageDataPointService;
import i9.defence.platform.datapush.utils.StringHelper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * 数据点消息处理服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:52:38
 */
@Service
public class ReceiveMessageDataPointServiceImpl implements ReceiveMessageDataPointService {

    /**
     * 处理上行数据消息
     * 
     * @param data
     */
    @Transactional
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
        deviceDataHisRepository.save(deviceDataHis);

        DeviceAttribute deviceAttribute = this.findAndCreateDeviceAttribute(String.valueOf(deviceId), datastream);

        this.deviceAttributeRepository.updateDeviceAttributeLastValue(value, new Date(at), deviceAttribute.getId());
    }

    public DeviceAttribute findAndCreateDeviceAttribute(String deviceId, String datastream) {
        DeviceAttribute deviceAttribute = this.deviceAttributeRepository.selectDeviceAttributeByDeviceIdAndDatastream(
                deviceId, datastream);
        if (deviceAttribute == null) {
            deviceAttribute = new DeviceAttribute(deviceId, datastream);
            this.deviceAttributeRepository.save(deviceAttribute);
        }

        return deviceAttribute;
    }

    @Autowired
    private DeviceDataHisRepository deviceDataHisRepository;

    @Autowired
    private DeviceAttributeRepository deviceAttributeRepository;
}

package i9.defence.platform.datapush.service.impl;

import i9.defence.platform.datapush.entity.DeviceAttribute;
import i9.defence.platform.datapush.entity.DeviceInfo;
import i9.defence.platform.datapush.respository.DeviceAttributeRepository;
import i9.defence.platform.datapush.respository.DeviceInfoRepository;
import i9.defence.platform.datapush.service.DeviceService;
import i9.defence.platform.datapush.utils.DateUtil;
import i9.defence.platform.datapush.utils.HttpClientUtil;
import i9.defence.platform.datapush.utils.StringHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Autowired
    private DeviceAttributeRepository deviceAttributeRepository;

    @Override
    public List<DeviceInfo> getDeviceInfoList() {
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        List<DeviceInfo> List = this.deviceInfoRepository.findAll(sort);
        return List;
    }

    @Override
    public DeviceInfo getDeviceInfoById(String id) {
        DeviceInfo deviceInfo = this.deviceInfoRepository.findOne(id);
        return deviceInfo;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void deleteDevice(String id) {
        this.deviceInfoRepository.delete(id);
    }

    @Override
    public void saveDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfoRepository.save(deviceInfo);
    }

    @Override
    public HashMap<String, DeviceAttribute> getDeviceAttributeValueResult(String deviceId) {
        List<DeviceAttribute> deviceAttributes = this.deviceAttributeRepository.selectDeviceAttributeListByDeviceId(deviceId);
        HashMap<String, DeviceAttribute> dataMap = new HashMap<String, DeviceAttribute>();
        for (DeviceAttribute deviceAttribute : deviceAttributes) {
            dataMap.put(deviceAttribute.getDatastream(), deviceAttribute);
        }
        return dataMap;
    }

    @Override
    public void saveDeviceAttribute(DeviceAttribute deviceAttribute) {
        this.deviceAttributeRepository.save(deviceAttribute);
    }

    @Override
    public void updateDeviceAttribute(DeviceAttribute deviceAttribute) {
        this.deviceAttributeRepository.updateDeviceAttributeLastValue(deviceAttribute.getValue(), deviceAttribute.getUpdateDate(), deviceAttribute.getId());
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void refreshDevice(String deviceId) throws Exception {
        DeviceInfo deviceInfo = this.getDeviceInfoById(deviceId);
        String resp = HttpClientUtil.doGet("http://api.heclouds.com/devices/datapoints?devIds=" + deviceInfo.getDeviceId());
        JSONObject result = new JSONObject(resp);
        if (result.getInt("errno") != 0) {
            throw new RuntimeException(result.getString("error"));
        }
        HashMap<String, DeviceAttribute> deviceAttributes = this.getDeviceAttributeValueResult(deviceInfo.getDeviceId());
        JSONObject data = result.getJSONObject("data");
        JSONArray devices = data.getJSONArray("devices");

        List<DeviceAttribute> saveDeviceAttributes = new ArrayList<DeviceAttribute>();
        for (int index = 0; index < devices.length(); index++) {
            JSONArray datastreams = devices.getJSONObject(index).getJSONArray("datastreams");
            for (int i = 0; i < datastreams.length(); i++) {
                JSONObject jsonObject = datastreams.getJSONObject(i);
                String datastream = jsonObject.getString("id");
                String at = jsonObject.getString("at");
                String value = String.valueOf(jsonObject.get("value"));
                DeviceAttribute deviceAttribute = deviceAttributes.get(datastream);
                if (deviceAttribute == null) {
                    deviceAttribute = new DeviceAttribute(deviceInfo.getDeviceId(), datastream);
                    saveDeviceAttributes.add(deviceAttribute);
                }
                deviceAttribute.setDatastream(datastream);
                deviceAttribute.setUpdateDate(DateUtil.parse(at));
                deviceAttribute.setValue(value);
            }
        }
        for (DeviceAttribute deviceAttribute : deviceAttributes.values()) {
            this.updateDeviceAttribute(deviceAttribute);
        }
        for (DeviceAttribute deviceAttribute : saveDeviceAttributes) {
            this.saveDeviceAttribute(deviceAttribute);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void addDevice(String deviceId) throws Exception {
        String resp = HttpClientUtil.doGet("http://api.heclouds.com/devices/" + deviceId);
        JSONObject result = new JSONObject(resp);
        if (result.getInt("errno") != 0) {
            throw new RuntimeException(result.getString("error"));
        }
        JSONObject data = result.getJSONObject("data");
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceId(deviceId);
        deviceInfo.setDeviceName(data.getString("title"));
        deviceInfo.setCreateDate(DateUtil.parse(data.getString("create_time")));
        deviceInfo.setImei(data.getString("imsi"));
        deviceInfo.setId(StringHelper.randomUUIDStr());
        deviceInfo.setPowerState(data.getBoolean("online") ? 1 : 0);
        this.saveDeviceInfo(deviceInfo);
    }
}

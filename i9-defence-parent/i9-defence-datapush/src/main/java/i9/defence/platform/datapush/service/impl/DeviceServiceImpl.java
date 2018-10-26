package i9.defence.platform.datapush.service.impl;

import i9.defence.platform.datapush.entity.DeviceAttribute;
import i9.defence.platform.datapush.entity.DeviceInfo;
import i9.defence.platform.datapush.respository.DeviceAttributeRepository;
import i9.defence.platform.datapush.respository.DeviceInfoRepository;
import i9.defence.platform.datapush.service.DeviceService;
import i9.defence.platform.datapush.utils.DateUtil;
import i9.defence.platform.datapush.utils.HttpClientUtil;
import i9.defence.platform.datapush.utils.PowerStateEnum;
import i9.defence.platform.datapush.utils.StringHelper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 设备信息服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:42:15
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Autowired
    private DeviceAttributeRepository deviceAttributeRepository;

    /**
     * 获取设备列表
     * 
     * @return
     */
    @Override
    public List<DeviceInfo> getDeviceInfoList() {
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        List<DeviceInfo> list = this.deviceInfoRepository.findAll(sort);
        return list;
    }

    /**
     * 通过ID获取设备详情
     * 
     * @param id
     * @return
     */
    @Override
    public DeviceInfo getDeviceInfoById(String id) {
        DeviceInfo deviceInfo = this.deviceInfoRepository.findOne(id);
        return deviceInfo;
    }

    /**
     * 删除设备
     * 
     * @param id
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void deleteDevice(String id) {
        this.deviceInfoRepository.delete(id);
    }

    /**
     * 保存设备信息
     * 
     * @param deviceInfo
     */
    @Override
    public void saveDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfoRepository.save(deviceInfo);
    }

    /**
     * 获取设备属性
     * 
     * @param deviceId
     * @return
     */
    @Override
    public HashMap<String, DeviceAttribute> getDeviceAttributeValueResult(String deviceId) {
        List<DeviceAttribute> deviceAttributes = this.deviceAttributeRepository
                .selectDeviceAttributeListByDeviceId(deviceId);
        HashMap<String, DeviceAttribute> dataMap = new HashMap<String, DeviceAttribute>(deviceAttributes.size());
        for (DeviceAttribute deviceAttribute : deviceAttributes) {
            dataMap.put(deviceAttribute.getDatastream(), deviceAttribute);
        }
        return dataMap;
    }

    /**
     * 保存设备属性
     * 
     * @param deviceAttribute
     */
    @Override
    public void saveDeviceAttribute(DeviceAttribute deviceAttribute) {
        this.deviceAttributeRepository.save(deviceAttribute);
    }

    /**
     * 更新设备属性
     * 
     * @param deviceAttribute
     */
    @Override
    public void updateDeviceAttribute(DeviceAttribute deviceAttribute) {
        this.deviceAttributeRepository.updateDeviceAttributeLastValue(deviceAttribute.getValue(),
                deviceAttribute.getUpdateDate(), deviceAttribute.getId());
    }

    /**
     * 同步设备
     * 
     * @param deviceId
     * @throws Exception
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void refreshDevice(String id) throws Exception {
        DeviceInfo deviceInfo = this.getDeviceInfoById(id);
        String resp = HttpClientUtil.doGet("http://api.heclouds.com/devices/datapoints?devIds="
                + deviceInfo.getDeviceId());
        JSONObject result = new JSONObject(resp);
        if (result.getInt("errno") != 0) {
            throw new RuntimeException(result.getString("error"));
        }
        HashMap<String, DeviceAttribute> deviceAttributes = this
                .getDeviceAttributeValueResult(deviceInfo.getDeviceId());
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

    /**
     * 添加设备
     * 
     * @param deviceId
     * @throws Exception
     */
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

    /**
     * 通过设备编号列表查询设备
     * 
     * @param ids
     * @return
     */
    @Override
    public List<DeviceInfo> getDeviceInfoListByIds(List<String> ids) {
        List<DeviceInfo> deviceInfos = this.deviceInfoRepository.queryDeviceInfoListByIds(ids);
        return deviceInfos;
    }

    @Override
    public void updateDeviceInfoPowerState(PowerStateEnum powerStateEnum, String id) {
        this.deviceInfoRepository.updateDeviceInfoPowerState(powerStateEnum.getValue(), id);
    }

    @Override
    public DeviceInfo selectDeviceInfoByDeviceId(String deviceId) {
        DeviceInfo deviceInfo = this.deviceInfoRepository.selectDeviceInfoByDeviceId(deviceId);
        return deviceInfo;
    }

    @Override
    public void refreshDeviceInfoPowerState(String deviceId, PowerStateEnum powerStateEnum) {
        DeviceInfo deviceInfo = this.selectDeviceInfoByDeviceId(deviceId);
        if (deviceInfo == null) {
            return;
        }
        this.updateDeviceInfoPowerState(powerStateEnum, deviceInfo.getId());
    }
    
    @Override
    public DeviceAttribute getAndCreateDeviceAttribute(String deviceId, String datastream) {
        DeviceAttribute deviceAttribute = this.deviceAttributeRepository
                .selectDeviceAttributeByDeviceIdAndDatastream(deviceId, datastream);
        if (deviceAttribute == null) {
            deviceAttribute = new DeviceAttribute(deviceId, datastream);
            this.deviceAttributeRepository.save(deviceAttribute);
        }

        return deviceAttribute;
    }

    @Override
    public void updateDeviceAttributeLastValue(String value, Date date, String id) {
        this.deviceAttributeRepository.updateDeviceAttributeLastValue(value, date, id);
    }
}

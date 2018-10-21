package i9.defence.platform.datapush.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.datapush.config.DeviceGroupAttributeNameCache;
import i9.defence.platform.datapush.dto.DeviceAttributeDto;
import i9.defence.platform.datapush.dto.DeviceDataHisDto;
import i9.defence.platform.datapush.dto.DeviceInfoDto;
import i9.defence.platform.datapush.entity.DeviceAttribute;
import i9.defence.platform.datapush.entity.DeviceDataHis;
import i9.defence.platform.datapush.entity.DeviceInfo;
import i9.defence.platform.datapush.service.DeviceDataHisService;
import i9.defence.platform.datapush.service.DeviceService;
import i9.defence.platform.datapush.utils.HttpResponseUtil;
import i9.defence.platform.datapush.utils.HttpResult;

@RestController
@RequestMapping(value = "/baseAPI/")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceGroupAttributeNameCache deviceGroupAttributeNameCache;

    @Autowired
    private DeviceDataHisService deviceDataHisService;

    /**
     * 添加设备
     * 
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/addDevice.sapi")
    @ResponseBody
    public HttpResult<?> addDevice(@RequestBody String deviceId) {
        try {
            this.deviceService.addDevice(deviceId);
            return HttpResponseUtil.ok();
        } catch (Exception e) {
            return HttpResponseUtil.error("设备添加失败");
        }
    }

    /**
     * 删除设备
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteDevice.sapi")
    @ResponseBody
    public HttpResult<?> deleteDevice(@RequestBody String id) {
        try {
            this.deviceService.deleteDevice(id);
            return HttpResponseUtil.ok();
        } catch (Exception e) {
            return HttpResponseUtil.error("删除设备失败");
        }
    }

    /**
     * 同步更新设备
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/refreshDevice.sapi")
    @ResponseBody
    public HttpResult<?> refreshDevice(@RequestBody String id) {
        try {
            this.deviceService.refreshDevice(id);
            return HttpResponseUtil.ok();
        } catch (Exception e) {
            return HttpResponseUtil.error("同步设备失败");
        }
    }

    /**
     * 查看设备详情
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/deviceDetails.sapi")
    @ResponseBody
    public HttpResult<?> deviceDetails(@RequestBody String id) {
        try {
            DeviceInfo deviceInfo = this.deviceService.getDeviceInfoById(id);
            if (deviceInfo == null) {
                return HttpResponseUtil.error("设备不存在");
            }
            DeviceInfoDto deviceInfoDto = DeviceInfoDto.build(deviceInfo.getId(), deviceInfo.getDeviceId(),
                    deviceInfo.getDeviceName(), deviceInfo.getImei(), deviceInfo.getPowerState(),
                    deviceInfo.getCreateDate());
            return HttpResponseUtil.ok(deviceInfoDto);
        } catch (Exception e) {
            return HttpResponseUtil.error("获取设备详情失败");
        }
    }

    /**
     * 获取设备信息及属性
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/deviceAttribute.sapi")
    @ResponseBody
    public HttpResult<?> deviceAttribute(@RequestBody String id) {
        try {
            DeviceInfo deviceInfo = this.deviceService.getDeviceInfoById(id);
            if (deviceInfo == null) {
                return HttpResponseUtil.error("设备不存在");
            }

            HashMap<String, DeviceAttribute> deviceAttributeValueResult = this.deviceService
                    .getDeviceAttributeValueResult(deviceInfo.getDeviceId());
            LinkedHashMap<String, String> attributeNames = deviceGroupAttributeNameCache
                    .getDeviceGroupAttributeResult("1");

            List<DeviceAttributeDto> deviceAttributeDtos = new ArrayList<DeviceAttributeDto>();
            for (Entry<String, String> entry : attributeNames.entrySet()) {
                DeviceAttribute deviceAttribute = deviceAttributeValueResult.get(entry.getKey());
                DeviceAttributeDto deviceAttributeDto = new DeviceAttributeDto();
                deviceAttributeDto.setAttributeName(entry.getValue());
                deviceAttributeDto.setDatastream(deviceAttribute.getDatastream());
                deviceAttributeDto.setValue(deviceAttribute.getValue());
                deviceAttributeDtos.add(deviceAttributeDto);
            }
            return HttpResponseUtil.ok(deviceAttributeDtos);
        } catch (Exception e) {
            return HttpResponseUtil.error("获取设备信息及属性失败");
        }
    }

    @RequestMapping(value = "/searchDeviceList.sapi")
    @ResponseBody
    public HttpResult<?> searchDeviceList(@RequestBody List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return HttpResponseUtil.error("查询设备列表ids为空");
        }
        try {
            List<DeviceInfo> deviceInfos = this.deviceService.getDeviceInfoListByIds(ids);
            List<DeviceInfoDto> deviceInfoDtos = new ArrayList<DeviceInfoDto>();
            for (DeviceInfo deviceInfo : deviceInfos) {
                DeviceInfoDto deviceInfoDto = DeviceInfoDto.build(deviceInfo.getId(), deviceInfo.getDeviceId(),
                        deviceInfo.getDeviceName(), deviceInfo.getImei(), deviceInfo.getPowerState(),
                        deviceInfo.getCreateDate());
                deviceInfoDtos.add(deviceInfoDto);
            }
            return HttpResponseUtil.ok(deviceInfoDtos);
        } catch (Exception e) {
            return HttpResponseUtil.error("获取设备列表失败");
        }
    }

    /**
     * 查看设备列表
     * 
     * @return
     */
    @RequestMapping(value = "/deviceList.sapi")
    @ResponseBody
    public HttpResult<?> deviceList() {
        try {
            List<DeviceInfo> deviceInfos = this.deviceService.getDeviceInfoList();
            List<DeviceInfoDto> deviceInfoDtos = new ArrayList<DeviceInfoDto>();
            for (DeviceInfo deviceInfo : deviceInfos) {
                DeviceInfoDto deviceInfoDto = DeviceInfoDto.build(deviceInfo.getId(), deviceInfo.getDeviceId(),
                        deviceInfo.getDeviceName(), deviceInfo.getImei(), deviceInfo.getPowerState(),
                        deviceInfo.getCreateDate());
                deviceInfoDtos.add(deviceInfoDto);
            }
            return HttpResponseUtil.ok(deviceInfoDtos);
        } catch (Exception e) {
            return HttpResponseUtil.error("获取设备列表失败");
        }
    }

    @RequestMapping(value = "/deviceDatapoint.sapi")
    @ResponseBody
    public HttpResult<?> deviceDatapoint(@RequestBody String jsonStr) {
        JSONObject param = new JSONObject(jsonStr);
        String id = param.getString("id");
        String datastream = param.getString("datastream");
        String startDate = param.getString("startDate");
        String endDate = param.getString("endDate");
        try {
            DeviceInfo deviceInfo = this.deviceService.getDeviceInfoById(id);
            if (deviceInfo == null) {
                return HttpResponseUtil.error("设备不存在");
            }
            final String deviceId = deviceInfo.getDeviceId();
            List<DeviceDataHis> deviceDataHisList = this.deviceDataHisService.queryDeviceDataHisDto(deviceId,
                    datastream, startDate, endDate);
            List<DeviceDataHisDto> deviceDataHisDtos = new ArrayList<DeviceDataHisDto>();
            for (DeviceDataHis deviceDataHis : deviceDataHisList) {
                DeviceDataHisDto deviceDataHisDto = new DeviceDataHisDto();
                deviceDataHisDto.setValue(deviceDataHis.getValue());
                deviceDataHisDto.setCreateDate(deviceDataHis.getCreateDate());
                deviceDataHisDtos.add(deviceDataHisDto);
            }
            return HttpResponseUtil.ok(deviceDataHisDtos);
        } catch (Exception e) {
            return HttpResponseUtil.error("获取设备数据点失败");
        }
    }
}

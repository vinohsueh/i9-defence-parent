package i9.defence.platform.datapush.controller;

import i9.defence.platform.datapush.entity.DeviceInfo;
import i9.defence.platform.datapush.service.DeviceService;
import i9.defence.platform.datapush.utils.DateUtil;
import i9.defence.platform.datapush.utils.HttpClientUtil;
import i9.defence.platform.datapush.utils.HttpResponseUtil;
import i9.defence.platform.datapush.utils.HttpResult;
import i9.defence.platform.datapush.utils.StringHelper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/baseAPI/")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/addDevice.sapi")
    @ResponseBody
    public HttpResult<?> addDevice(String deviceId) {
        try {
            String resp = HttpClientUtil.doGet("http://api.heclouds.com/devices/" + deviceId);
            JSONObject result = new JSONObject(resp);
            if (result.getInt("errno") != 0) {
                return HttpResponseUtil.error(result.getString("error"));
            }
            JSONObject data = result.getJSONObject("data");

            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setDeviceId(deviceId);
            deviceInfo.setDeviceName(data.getString("title"));
            deviceInfo.setCreateDate(DateUtil.parse(data.getString("create_time")));
            deviceInfo.setImei(data.getString("imsi"));
            deviceInfo.setId(StringHelper.randomUUIDStr());
            deviceInfo.setPowerState(data.getBoolean("online") ? 1 : 0);
            this.deviceService.saveDeviceInfo(deviceInfo);

            return HttpResponseUtil.ok();
        } catch (Exception e) {
            return HttpResponseUtil.error("设备添加失败");
        }
    }

    @RequestMapping(value = "/deleteDevice.sapi")
    @ResponseBody
    public HttpResult<?> deleteDevice(String deviceId) {
        try {
            this.deviceService.deleteDevice(deviceId);
            return HttpResponseUtil.ok();
        } catch (Exception e) {
            return HttpResponseUtil.error("删除设备失败");
        }
    }

    @RequestMapping(value = "/deviceDetails.sapi")
    @ResponseBody
    public HttpResult<?> deviceDetails(String deviceId) {
        try {
            DeviceInfo deviceInfo = this.deviceService.getDeviceInfoById(deviceId);
            if (deviceInfo == null) {
                return HttpResponseUtil.error("设备不存在");
            }
            return HttpResponseUtil.ok(deviceInfo);
        } catch (Exception e) {
            return HttpResponseUtil.error("获取设备详情失败");
        }
    }

    @RequestMapping(value = "/deviceList.sapi")
    @ResponseBody
    public HttpResult<?> deviceList() {
        try {
            List<DeviceInfo> deviceInfos = this.deviceService.getDeviceInfoList();
            return HttpResponseUtil.ok(deviceInfos);
        } catch (Exception e) {
            return HttpResponseUtil.error("获取设备列表失败");
        }
    }
}

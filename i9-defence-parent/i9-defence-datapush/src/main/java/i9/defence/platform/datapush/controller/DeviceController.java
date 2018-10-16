package i9.defence.platform.datapush.controller;

import i9.defence.platform.datapush.entity.DeviceInfo;
import i9.defence.platform.datapush.service.DeviceService;
import i9.defence.platform.datapush.utils.HttpResponseUtil;
import i9.defence.platform.datapush.utils.HttpResult;
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
            this.deviceService.addDevice(deviceId);
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

    @RequestMapping(value = "/refreshDevice.sapi")
    @ResponseBody
    public HttpResult<?> refreshDevice(String deviceId) {
        try {
            this.deviceService.refreshDevice(deviceId);
            return HttpResponseUtil.ok();
        } catch (Exception e) {
            return HttpResponseUtil.error("同步设备失败");
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

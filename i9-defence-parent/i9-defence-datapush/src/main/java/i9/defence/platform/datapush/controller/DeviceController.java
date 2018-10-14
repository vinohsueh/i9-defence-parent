package i9.defence.platform.datapush.controller;

import i9.defence.platform.datapush.service.DeviceService;
import i9.defence.platform.datapush.utils.HttpClientUtil;
import i9.defence.platform.datapush.utils.HttpResponseUtil;
import i9.defence.platform.datapush.utils.HttpResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/deviceList.xhtml")
    public String deviceList() {
        return "deviceList";
    }

    @RequestMapping(value = "/deviceDetails.xhtml")
    public String deviceDetails() {
        return "deviceDetails";
    }

    @RequestMapping(value = "/addDevice.xapi")
    @ResponseBody
    public HttpResult<?> addDevice(String deviceId) {
        try {
            HttpClientUtil.doGet("");
            return HttpResponseUtil.ok();
        } catch (Exception e) {
            return HttpResponseUtil.error("device ont find");
        }
    }

    @RequestMapping(value = "/deleteDevice.xapi")
    @ResponseBody
    public HttpResult<?> deleteDevice(String deviceId) {
        try {
            this.deviceService.deleteDevice(deviceId);
            return HttpResponseUtil.ok();
        } catch (Exception e) {
            return HttpResponseUtil.error("device ont find");
        }

    }
}

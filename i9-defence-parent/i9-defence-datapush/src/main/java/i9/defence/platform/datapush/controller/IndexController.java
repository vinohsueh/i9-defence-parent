package i9.defence.platform.datapush.controller;

import i9.defence.platform.datapush.entity.DeviceInfo;
import i9.defence.platform.datapush.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = {"/index.shtml", "/"})
    public String index(Model model) {
        List<DeviceInfo> deviceInfos = this.deviceService.getDeviceInfoList();
        model.addAttribute("deviceInfos", deviceInfos);
        return "index";
    }

    @RequestMapping(value = {"/details-{deviceId}.shtml"})
    public String details(@PathVariable("deviceId") String deviceId, Model model) {
        DeviceInfo deviceInfo = this.deviceService.getDeviceInfoById(deviceId);
        model.addAttribute("deviceInfo", deviceInfo);
        return "details";
    }
}
package i9.defence.platform.datapush.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class DeviceController {

    @RequestMapping(value = "/deviceList")
    public String deviceList() {
        return "deviceList";
    }

    @RequestMapping(value = "/deviceDetails")
    public String deviceDetails() {
        return "deviceDetails";
    }
}

package i9.defence.platform.datapush.data;

import i9.defence.platform.datapush.entity.DeviceGroupAttribute;
import i9.defence.platform.datapush.respository.DeviceGroupAttributeRepository;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceGroupAttributeNameCache {

    private HashMap<String, String> dataMap = new HashMap<String, String>();

    @Autowired
    private DeviceGroupAttributeRepository deviceGroupAttributeRepository;

    public void reInit() {
        List<DeviceGroupAttribute> list = this.deviceGroupAttributeRepository
                .findAll();
        HashMap<String, String> dataMap = new HashMap<String, String>();
        for (DeviceGroupAttribute deviceGroupAttribute : list) {
            dataMap.put(deviceGroupAttribute.getDeviceGroupId() + "_" + deviceGroupAttribute.getDatastream(),
                    deviceGroupAttribute.getAttribute());
        }
        this.dataMap = dataMap;
    }

    public String getDeviceGroupAttributeName(String value) {
        String name = this.dataMap.get(value);
        if (name == null) {
            name = "";
        }
        return name;
    }
}

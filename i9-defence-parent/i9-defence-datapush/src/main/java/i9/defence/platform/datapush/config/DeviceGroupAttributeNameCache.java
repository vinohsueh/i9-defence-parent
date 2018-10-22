package i9.defence.platform.datapush.config;

import i9.defence.platform.datapush.entity.DeviceGroupAttribute;
import i9.defence.platform.datapush.respository.DeviceGroupAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 设备组属性名映射缓存
 * 
 * @author R12
 * @date 2018年10月22日 14:06:38
 */
@Service
public class DeviceGroupAttributeNameCache {

    private HashMap<String, LinkedHashMap<String, String>> dataMap = new HashMap<String, LinkedHashMap<String, String>>();

    @Autowired
    private DeviceGroupAttributeRepository deviceGroupAttributeRepository;

    /**
     * 初始化dataMap
     */
    public void reInit() {
        List<DeviceGroupAttribute> list = this.deviceGroupAttributeRepository.findAll();
        HashMap<String, LinkedHashMap<String, String>> dataMap = new HashMap<String, LinkedHashMap<String, String>>();
        for (DeviceGroupAttribute deviceGroupAttribute : list) {
            LinkedHashMap<String, String> tmpData = dataMap.get(deviceGroupAttribute.getDeviceGroupId());
            if (tmpData == null) {
                tmpData = new LinkedHashMap<String, String>();
                dataMap.put(deviceGroupAttribute.getDeviceGroupId(), tmpData);
            }
            tmpData.put(deviceGroupAttribute.getDatastream(), deviceGroupAttribute.getAttribute());
        }
        this.dataMap = dataMap;
    }

    /**
     * 获取设备组数据流属性名称
     * 
     * @param deviceGroupId
     * @param datastream
     * @return
     */
    public String getDeviceGroupAttributeName(String deviceGroupId, String datastream) {
        LinkedHashMap<String, String> tmpData = this.dataMap.get(deviceGroupId);
        if (tmpData == null) {
            return "";
        }
        return tmpData.get(datastream);
    }

    /**
     * 获取设备组属性映射
     * 
     * @param deviceGroupId
     * @return
     */
    public LinkedHashMap<String, String> getDeviceGroupAttributeResult(String deviceGroupId) {
        LinkedHashMap<String, String> tmpData = this.dataMap.get(deviceGroupId);
        return tmpData;
    }
}

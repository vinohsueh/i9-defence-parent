package i9.defence.platform.aliyun;

import com.aliyun.iot.as.bridge.core.config.DeviceConfigManager;
import com.aliyun.iot.as.bridge.core.model.DeviceIdentity;

public class SimeleDeviceConfigManager implements DeviceConfigManager {

    @Override
    public DeviceIdentity getDeviviceIdentity(String originalIdentity) {
        String productKey = "a16IzBxrD85";
        String deviceName = "Iyub03hvXHIaBqDSaJz4";
        String deviceSecret = "iZAwYkBl8VfSdyYOxM9jALhj5gOzC6dR";
        DeviceIdentity deviceIdentity = new DeviceIdentity(productKey, deviceName, deviceSecret);
        return deviceIdentity;
    }

    @Override
    public String getOriginalIdentity(String productKey, String deviceName) {
        return this.formatKey(productKey, deviceName);
    }

    private String formatKey(String productKey, String deviceName) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(productKey).append("_").append(deviceName);
        return stringBuffer.toString();
    }

}

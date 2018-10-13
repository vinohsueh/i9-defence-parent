package i9.defence.platform.datapush.config;

import i9.defence.platform.datapush.data.DeviceGroupAttributeNameCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        deviceGroupAttributeNameCache.reInit();
    }
    
    @Autowired
    private DeviceGroupAttributeNameCache deviceGroupAttributeNameCache;
}

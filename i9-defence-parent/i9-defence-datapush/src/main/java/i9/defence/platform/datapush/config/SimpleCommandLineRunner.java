package i9.defence.platform.datapush.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * spring boot 启动后执行
 * 
 * @author R12
 * @date 2018年10月22日 14:08:48
 */
@Component
public class SimpleCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        deviceGroupAttributeNameCache.reInit();
    }

    @Autowired
    private DeviceGroupAttributeNameCache deviceGroupAttributeNameCache;
}

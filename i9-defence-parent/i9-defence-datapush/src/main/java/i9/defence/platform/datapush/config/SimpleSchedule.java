package i9.defence.platform.datapush.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleSchedule {

    @Autowired
    private DeviceGroupAttributeNameCache deviceGroupAttributeNameCache;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SimpleSchedule.class);

    @Scheduled(fixedDelay = 10 * 1000)
    public void loadDeviceGroupAttribute() {
        try {
            deviceGroupAttributeNameCache.reInit();
            LOGGER.info("load success");
        } catch (Exception exception) {
            LOGGER.info("load error", exception);
        }
    }
}

package i9.defence.platform.datapush.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 任务调度类
 * 
 * @author R12
 * @date 2018年10月22日 14:09:16
 */
@Component
public class SimpleSchedule {

    @Autowired
    private DeviceGroupAttributeNameCache deviceGroupAttributeNameCache;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSchedule.class);

    /**
     * 每10秒钟重新加载设备组属性映射
     */
    @Scheduled(fixedDelay = 10 * 1000)
    public void loadDeviceGroupAttribute() {
        try {
            deviceGroupAttributeNameCache.reInit();
        } catch (Exception exception) {
            LOGGER.info("加载设备组属性失败", exception);
        }
    }
}

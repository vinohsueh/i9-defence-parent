package i9.defence.platform.microservice.push.service.impl;

import i9.defence.platform.microservice.push.service.ThirdPlatformService;
import i9.defence.platform.utils.TargetDataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 与第三方通信接口
 * 
 * @author lby
 * @version
 * 
 */
@Service
@Transactional
public class ThirdPlatformServiceImpl implements ThirdPlatformService {

    @TargetDataSource("hjxfweb")
    @Override
    public void saveAlertOrigin(String text) throws Exception {
        // 将数据添加到通道数据表中
    }
}

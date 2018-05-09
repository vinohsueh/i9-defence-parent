package i9.defence.platform.microservice.push.service.impl;

import i9.defence.platform.microservice.push.service.ThirdPlatformService;
import i9.defence.platform.microservice.push.util.TargetDataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 创建时间：2018年5月7日 下午1:55:48
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

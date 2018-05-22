package i9.defence.platform.microservice.push.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.microservice.push.repository.ThirdPlatformRepository;
import i9.defence.platform.microservice.push.service.ThirdPlatformService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.TargetDataSource;

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
	
	@Autowired
	private ThirdPlatformRepository thirdPlatformRepository;
	
    @TargetDataSource("hjxfweb")
    @Override
    public void saveAlertOrigin(String text) throws Exception {
        // 将数据添加到通道数据表中
    }
    
    /**
     * 每天建当日的表
     */
    @TargetDataSource("hjxfweb")
	@Override
	public void createTableEveryday() {
    	try {
			thirdPlatformRepository.createTableEveryday();
		} catch (SQLException e) {
			throw new BusinessException("建表失败");
		}
	}
}

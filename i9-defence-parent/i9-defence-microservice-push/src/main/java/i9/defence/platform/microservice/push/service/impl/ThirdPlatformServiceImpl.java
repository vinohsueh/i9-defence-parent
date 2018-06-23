package i9.defence.platform.microservice.push.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.microservice.push.repository.ThirdPlatformRepository;
import i9.defence.platform.microservice.push.service.ThirdPlatformService;
import i9.defence.platform.microservice.push.vo.ChannelData;
import i9.defence.platform.microservice.push.vo.DeviceError;
import i9.defence.platform.microservice.push.vo.DeviceInfoDto;
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
    public void saveAlertOrigin(DeviceInfoDto deviceInfoDto,List<ChannelData> list){
    	try {
    		if (list.size() > 0) {
    			thirdPlatformRepository.insertIntoOldPlat(deviceInfoDto,list);
    		}
		} catch (BusinessException e) {
			throw new BusinessException("建表失败");
		}
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

	@Override
	public DeviceInfoDto selectEquipmentInfo(String deviceId) {
		try {
			DeviceInfoDto deviceInfoDto = thirdPlatformRepository.selectEquipmentInfo(deviceId);
			return deviceInfoDto;
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorMessage(),"查询设备信息失败");
		}
		
	}

	@Override
	public Map<String, String> selectDeviceErrors() {
		try {
			Map<String, String> map = new HashMap<String, String>();
			List<DeviceError> list = thirdPlatformRepository.selectDeviceErrors();
			for (DeviceError deviceError : list) {
				map.put(deviceError.getCode()+deviceError.getCatagoryId(), deviceError.getName());
			}
			return map;
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorMessage(),"查询设备错误失败");
		}
		
	}
	
	@TargetDataSource("xfjcxt")
	@Override
	public void updateDeviceStatus(int id,int num) throws BusinessException {
		try {
			String status = "01";
			if (num > 0) {
				status = "03";
			}
			thirdPlatformRepository.updateDeviceStatus(id,status);
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorMessage(),"更新设备状态失败");
		}
	}

	@Override
	public List<Integer> selectUsefulChannel(String deviceId) throws BusinessException {
		try {
			return thirdPlatformRepository.selectUsefulChannel(deviceId);
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorMessage(),"查询设备通道失败");
		}
	}
}

package i9.defence.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.service.ThirdPlatformService;
import i9.defence.platform.service.dto.DeviceError;
import i9.defence.platform.service.dto.DeviceInfoDto;
import i9.defence.platform.service.repository.ThirdPlatformRepository;
import i9.defence.platform.utils.BusinessException;

/*
 * @ClassName: ThirdPlatformServiceImpl 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月14日 上午10:24:40
 */
@Service
@Transactional
public class ThirdPlatformServiceImpl implements ThirdPlatformService {

	@Autowired
	private ThirdPlatformRepository thirdPlatformRepository;
	
	@Override
	public DeviceInfoDto selectEquipmentInfo(String text) throws BusinessException {
		try {
            DeviceInfoDto deviceInfoDto = thirdPlatformRepository.selectEquipmentInfo(text);
            return deviceInfoDto;
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage(), "查询设备信息失败");
        }
	}

	@Override
	public Map<String, String> selectDeviceErrors() throws BusinessException {
		 try {
	            Map<String, String> map = new HashMap<String, String>();
	            List<DeviceError> list = thirdPlatformRepository.selectDeviceErrors();
	            for (DeviceError deviceError : list) {
	                map.put(deviceError.getCode() + deviceError.getCatagoryId(), deviceError.getName());
	            }
	            return map;
	        } catch (BusinessException e) {
	            throw new BusinessException(e.getErrorMessage(), "查询设备错误失败");
	        }
	}

	@Override
	public List<Integer> selectUsefulChannel(String deviceId) throws BusinessException {
		try {
            return thirdPlatformRepository.selectUsefulChannel(deviceId);
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage(), "查询设备通道失败");
        }
	}

}

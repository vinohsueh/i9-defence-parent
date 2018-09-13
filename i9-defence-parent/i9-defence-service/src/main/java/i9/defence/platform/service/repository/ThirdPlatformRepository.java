package i9.defence.platform.service.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import i9.defence.platform.service.dto.DeviceError;
import i9.defence.platform.service.dto.DeviceInfoDto;

/**
 * @ClassName: ThirdPlatformRepository 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月30日 上午9:42:51
 */
@Repository
public interface ThirdPlatformRepository {
	
	 /**
     * 查询设备信息
     * @param deviceId
     * @param errorCode
     */
	DeviceInfoDto selectEquipmentInfo(String deviceId);
	
	/**
     * 查询所有的设备故障名称
     * @return
     */
	List<DeviceError> selectDeviceErrors();
	
	/**
     * 查询该设备所需要的通道
     * 
     * @param deviceId
     * @return
     */
    List<Integer> selectUsefulChannel(String deviceId);
}

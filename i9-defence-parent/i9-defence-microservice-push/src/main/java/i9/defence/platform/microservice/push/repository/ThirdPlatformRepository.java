package i9.defence.platform.microservice.push.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import i9.defence.platform.microservice.push.vo.ChannelData;
import i9.defence.platform.microservice.push.vo.DeviceError;
import i9.defence.platform.microservice.push.vo.DeviceInfoDto;

@Repository
public interface ThirdPlatformRepository {
	
	/**
	 * 每天创建当天的表
	 */
	void createTableEveryday() throws SQLException;
	
	  /**
     * 查询设备信息
     * @param deviceId
     * @param errorCode
     */
	DeviceInfoDto selectEquipmentInfo(String deviceId);
	
	/**
	 * 插入老平台
	 * @param deviceInfoDto
	 */
	void insertIntoOldPlat(DeviceInfoDto deviceInfoDto,List<ChannelData> list);
	
	/**
     * 查询所有的设备故障名称
     * @return
     */
	List<DeviceError> selectDeviceErrors();
	
	/**
	 * 更新设备状态
	 * @param id
	 */
	void updateDeviceStatus(int id,String status);
	
	/**
	 * 查询设备有用的通道
	 * @param deviceId
	 * @return
	 */
	List<Integer> selectUsefulChannel(String deviceId);
}

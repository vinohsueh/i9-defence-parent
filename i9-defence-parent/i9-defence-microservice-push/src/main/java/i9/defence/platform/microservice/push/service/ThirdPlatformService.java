package i9.defence.platform.microservice.push.service;

import java.util.List;
import java.util.Map;

import i9.defence.platform.microservice.push.vo.ChannelData;
import i9.defence.platform.microservice.push.vo.DeviceInfoDto;
import i9.defence.platform.utils.BusinessException;

/**
 * 创建时间：2018年5月7日 上午10:09:26
 * 
 * @author lby
 * @version
 */
public interface ThirdPlatformService {

    /**
     * 保存故障
     * 
     * @param text
     */
    void saveAlertOrigin(DeviceInfoDto deviceInfoDto,List<ChannelData> list) throws BusinessException;
    
    /**
     * 每天创建一张表
     */
    void createTableEveryday() throws BusinessException;
    
    /**
     * 查询设备信息
     * @param text
     * @return
     */
    DeviceInfoDto selectEquipmentInfo(String text) throws BusinessException;
    
    /**
     * 查询所有的设备故障名称
     * @return
     */
	Map<String, String> selectDeviceErrors() throws BusinessException;
	
	/**
	 * 更新设备状态
	 * @param id
	 */
	void updateDeviceStatus(int id,int num) throws BusinessException;
  
}

package i9.defence.platform.service;

import java.util.List;
import java.util.Map;

import i9.defence.platform.service.dto.DeviceInfoDto;
import i9.defence.platform.utils.BusinessException;

/**
 * 创建时间：2018年5月7日 上午10:09:26
 * 
 * @author lby
 * @version
 */
public interface ThirdPlatformService {

    /**
     * 查询设备信息
     * 
     * @param text
     * @return
     */
    DeviceInfoDto selectEquipmentInfo(String text) throws BusinessException;

    /**
     * 查询所有的设备故障名称
     * 
     * @return
     */
    Map<String, String> selectDeviceErrors() throws BusinessException;

    /**
     * 查询该设备所需要的通道
     * 
     * @param deviceId
     * @return
     */
    List<Integer> selectUsefulChannel(String deviceId) throws BusinessException;

}

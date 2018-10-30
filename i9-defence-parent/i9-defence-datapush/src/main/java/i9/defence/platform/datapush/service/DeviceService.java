package i9.defence.platform.datapush.service;

import i9.defence.platform.datapush.entity.DeviceAttribute;
import i9.defence.platform.datapush.entity.DeviceInfo;
import i9.defence.platform.datapush.utils.PowerStateEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 设备信息服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:42:15
 */
public interface DeviceService {

    /**
     * 获取设备列表
     * 
     * @return
     */
    List<DeviceInfo> getDeviceInfoList();

    /**
     * 通过ID获取设备详情
     * 
     * @param id
     * @return
     */
    DeviceInfo getDeviceInfoById(String id);

    /**
     * 删除设备
     * 
     * @param id
     */
    void deleteDevice(String id);

    /**
     * 保存设备信息
     * 
     * @param deviceInfo
     */
    void saveDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 获取设备属性
     * 
     * @param deviceId
     * @return
     */
    HashMap<String, DeviceAttribute> getDeviceAttributeValueResult(String deviceId);

    /**
     * 保存设备属性
     * 
     * @param deviceAttribute
     */
    void saveDeviceAttribute(DeviceAttribute deviceAttribute);

    /**
     * 更新设备属性
     * 
     * @param deviceAttribute
     */
    void updateDeviceAttribute(DeviceAttribute deviceAttribute);

    /**
     * 同步设备
     * 
     * @param deviceId
     * @throws Exception
     */
    void refreshDevice(String deviceId) throws Exception;

    /**
     * 添加设备
     * 
     * @param deviceId
     * @throws Exception
     */
    void addDevice(String deviceId) throws Exception;

    /**
     * 通过设备编号列表查询设备
     * 
     * @param ids
     * @return
     */
    List<DeviceInfo> getDeviceInfoListByIds(List<String> ids);

    /**
     * 更新设备状态信息
     * 
     * @param powerStateEnum
     * @param id
     */
    void updateDeviceInfoPowerState(PowerStateEnum powerStateEnum, String id);

    /**
     * 获取设备信息
     * 
     * @param deviceId
     * @return
     */
    DeviceInfo selectDeviceInfoByDeviceId(String deviceId);

    /**
     * 刷新设备状态
     * 
     * @param deviceId
     * @param powerStateEnum
     */
    void refreshDeviceInfoPowerState(String deviceId, PowerStateEnum powerStateEnum);

    /**
     * 更新设备属性值
     * 
     * @param value
     * @param date
     * @param id
     */
    void updateDeviceAttributeLastValue(String value, Date date, String id);

    /**
     * 获取或者创建一个设备属性
     * 
     * @param deviceId
     * @param datastream
     * @return
     */
    DeviceAttribute getAndCreateDeviceAttribute(String deviceId, String datastream);
}

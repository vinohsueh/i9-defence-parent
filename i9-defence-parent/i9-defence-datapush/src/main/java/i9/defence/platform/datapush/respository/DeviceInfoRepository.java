package i9.defence.platform.datapush.respository;

import i9.defence.platform.datapush.entity.DeviceInfo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 设备信息DAO
 * 
 * @author R12
 * @date 2018年10月22日 14:29:30
 */
public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, String> {

    /**
     * 通过deviceId查询设备信息
     * 
     * @param deviceId
     * @return
     */
    @Query("SELECT deviceInfo FROM DeviceInfo deviceInfo WHERE deviceInfo.deviceId = :deviceId")
    DeviceInfo selectDeviceInfoByDeviceId(@Param("deviceId") String deviceId);

    /**
     * 更新设备状态
     * 
     * @param powerState
     * @param id
     */
    @Modifying
    @Query("UPDATE DeviceInfo deviceInfo SET deviceInfo.powerState = :powerState WHERE deviceInfo.id = :id")
    void updateDeviceInfoPowerState(@Param("powerState") int powerState, @Param("id") String id);

    /**
     * 删除设备
     * 
     * @param deviceId
     */
    @Modifying
    @Query("DELETE FROM DeviceInfo deviceInfo WHERE deviceInfo.deviceId = :deviceId")
    void deleteDeviceInfoById(@Param("deviceId") String deviceId);

    /**
     * 通过设备编号列表查询设备
     * 
     * @param ids
     * @return
     */
    @Query("SELECT deviceInfo FROM DeviceInfo deviceInfo WHERE deviceInfo.id IN (?1)")
    List<DeviceInfo> queryDeviceInfoListByIds(List<String> ids);
}

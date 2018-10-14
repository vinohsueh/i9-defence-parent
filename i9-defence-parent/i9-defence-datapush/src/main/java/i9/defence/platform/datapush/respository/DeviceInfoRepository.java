package i9.defence.platform.datapush.respository;

import i9.defence.platform.datapush.entity.DeviceInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, String> {

    @Query("SELECT deviceInfo FROM DeviceInfo deviceInfo WHERE deviceInfo.deviceId = :deviceId")
    DeviceInfo selectDeviceInfoByDeviceId(@Param("deviceId") String deviceId);

    @Modifying
    @Query("UPDATE DeviceInfo deviceInfo SET deviceInfo.powerState = :powerState WHERE deviceInfo.id = :id")
    void updateDeviceInfoPowerState(@Param("powerState") int powerState, @Param("id") String id);

    @Modifying
    @Query("DELETE FROM DeviceInfo deviceInfo WHERE deviceInfo.deviceId = :deviceId")
    void deleteDeviceInfoById(@Param("deviceId") String deviceId);
}

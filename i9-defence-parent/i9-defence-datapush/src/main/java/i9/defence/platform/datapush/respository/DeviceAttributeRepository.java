package i9.defence.platform.datapush.respository;

import i9.defence.platform.datapush.entity.DeviceAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * 设备属性DAO
 * 
 * @author R12
 * @date 2018年10月22日 14:19:38
 */
public interface DeviceAttributeRepository extends JpaRepository<DeviceAttribute, String> {

    @Query("SELECT deviceAttribute FROM DeviceAttribute deviceAttribute WHERE deviceAttribute.deviceId = :deviceId AND deviceAttribute.datastream = :datastream")
    DeviceAttribute selectDeviceAttributeByDeviceIdAndDatastream(@Param("deviceId") String deviceId,
            @Param("datastream") String datastream);

    @Modifying
    @Query("UPDATE DeviceAttribute deviceAttribute SET deviceAttribute.value = :value, deviceAttribute.updateDate = :updateDate WHERE deviceAttribute.id = :id")
    void updateDeviceAttributeLastValue(@Param("value") String value, @Param("updateDate") Date updateDate,
            @Param("id") String id);

    @Query("SELECT deviceAttribute FROM DeviceAttribute deviceAttribute WHERE deviceAttribute.deviceId = :deviceId")
    List<DeviceAttribute> selectDeviceAttributeListByDeviceId(@Param("deviceId") String deviceId);
}

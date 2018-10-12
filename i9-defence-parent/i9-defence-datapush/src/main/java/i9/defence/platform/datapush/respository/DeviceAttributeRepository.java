package i9.defence.platform.datapush.respository;

import java.util.Date;

import i9.defence.platform.datapush.entity.DeviceAttribute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeviceAttributeRepository extends JpaRepository<DeviceAttribute, String> {

    @Query("SELECT deviceAttribute FROM DeviceAttribute deviceAttribute WHERE deviceAttribute.deviceId = :deviceId AND deviceAttribute.datastream = :datastream")
    DeviceAttribute selectDeviceAttributeByDeviceIdAndDatastream(@Param("deviceId") String deviceId,
            @Param("datastream") String datastream);

    @Modifying
    @Query("UPDATE DeviceAttribute deviceAttribute SET deviceAttribute.value = :value, deviceAttribute.updateDate = :updateDate WHERE deviceAttribute.id = :id")
    void updateDeviceAttributeLastValue(@Param("value") int value, @Param("updateDate") Date updateDate,
            @Param("id") String id);
}

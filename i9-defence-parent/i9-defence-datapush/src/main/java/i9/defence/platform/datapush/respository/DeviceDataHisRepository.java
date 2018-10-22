package i9.defence.platform.datapush.respository;

import i9.defence.platform.datapush.entity.DeviceDataHis;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 设备数据历史DAO
 * 
 * @author R12
 * @date 2018年10月22日 14:20:08
 */
public interface DeviceDataHisRepository extends JpaRepository<DeviceDataHis, String> {

    @Query("SELECT deviceDataHis FROM DeviceDataHis deviceDataHis WHERE deviceDataHis.deviceId = :deviceId "
            + " AND deviceDataHis.datastream = :datastream "
            + " AND deviceDataHis.createDate BETWEEN :startDate AND :endDate "
            + " ORDER BY deviceDataHis.createDate ASC")
    List<DeviceDataHis> queryDeviceDataHisDto(@Param("deviceId") String deviceId,
            @Param("datastream") String datastream, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}

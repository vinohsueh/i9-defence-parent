package i9.defence.platform.datapush.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import i9.defence.platform.datapush.entity.DeviceGroupAttribute;
import i9.defence.platform.datapush.entity.DeviceGroupInfo;

/**
 * 设备组信息DAO
 * 
 * @author R12
 * @date 2018年10月22日 14:29:06
 */
public interface DeviceGroupInfoRepository extends JpaRepository<DeviceGroupAttribute, String> {

    @Query("SELECT deviceGroupInfo FROM DeviceGroupInfo deviceGroupInfo WHERE deviceGroupInfo.id IN (?1)")
    List<DeviceGroupInfo> queryDeviceGroupInfoByIdList(List<String> idList);
}

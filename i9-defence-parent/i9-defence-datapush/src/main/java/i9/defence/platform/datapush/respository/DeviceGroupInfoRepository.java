package i9.defence.platform.datapush.respository;

import i9.defence.platform.datapush.entity.DeviceGroupAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 设备组信息DAO
 * 
 * @author R12
 * @date 2018年10月22日 14:29:06
 */
public interface DeviceGroupInfoRepository extends JpaRepository<DeviceGroupAttribute, String> {
}

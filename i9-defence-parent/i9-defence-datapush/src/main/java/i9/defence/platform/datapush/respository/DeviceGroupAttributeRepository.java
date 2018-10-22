package i9.defence.platform.datapush.respository;

import i9.defence.platform.datapush.entity.DeviceGroupAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 设备组属性DAO
 * 
 * @author R12
 * @date 2018年10月22日 14:25:00
 */
public interface DeviceGroupAttributeRepository extends JpaRepository<DeviceGroupAttribute, String> {
}

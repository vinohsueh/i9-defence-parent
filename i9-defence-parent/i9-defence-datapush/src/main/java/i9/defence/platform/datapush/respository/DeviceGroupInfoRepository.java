package i9.defence.platform.datapush.respository;

import i9.defence.platform.datapush.entity.DeviceGroupAttribute;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceGroupInfoRepository extends JpaRepository<DeviceGroupAttribute, String> {
}

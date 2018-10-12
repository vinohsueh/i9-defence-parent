package i9.defence.platform.datapush.respository;

import i9.defence.platform.datapush.entity.DeviceInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, String> {
}

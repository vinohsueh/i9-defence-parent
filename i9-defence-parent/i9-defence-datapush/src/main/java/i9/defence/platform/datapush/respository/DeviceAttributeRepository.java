package i9.defence.platform.datapush.respository;

import i9.defence.platform.datapush.entity.DeviceAttribute;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceAttributeRepository extends JpaRepository<DeviceAttribute, String> {
}

package i9.defence.platform.datapush.respository;

import i9.defence.platform.datapush.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

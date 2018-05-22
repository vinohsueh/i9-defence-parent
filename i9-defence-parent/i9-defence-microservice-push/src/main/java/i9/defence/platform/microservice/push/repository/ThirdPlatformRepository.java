package i9.defence.platform.microservice.push.repository;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPlatformRepository {
	
	/**
	 * 每天创建当天的表
	 */
	void createTableEveryday() throws SQLException;
}

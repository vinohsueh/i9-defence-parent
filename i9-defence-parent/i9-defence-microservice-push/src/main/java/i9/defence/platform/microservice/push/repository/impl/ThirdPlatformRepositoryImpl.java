package i9.defence.platform.microservice.push.repository.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import i9.defence.platform.microservice.push.repository.ThirdPlatformRepository;

@Repository
public class ThirdPlatformRepositoryImpl implements ThirdPlatformRepository {
	
	private static final String PREFIX = "device_record";
	
	private static final String REGRX = "yyyyMMdd";
	
	private static StringBuffer TABLE_SQL = null;
	
	static{
		StringBuffer tablesql = new StringBuffer("");  
		tablesql.append(" `num` int(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '序号',");          
		tablesql.append(" `device_code` int(6) UNSIGNED NOT NULL COMMENT '设备编号', ");  
		tablesql.append(" `time_history` datetime(0) NOT NULL COMMENT '时间',");  
		tablesql.append(" `create_time` datetime(0) NOT NULL COMMENT '创建时间',");  
		tablesql.append(" `record1` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录1',");  
		tablesql.append(" `record2` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录2',");  
		tablesql.append(" `record3` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录3',");  
		tablesql.append(" `record_usr_log` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标记',");  
		tablesql.append(" `misinformation` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',");  
		tablesql.append(" PRIMARY KEY (`num`) USING BTREE,");  
		tablesql.append(" INDEX `index_device_code`(`device_code`) USING BTREE,");  
		tablesql.append(" INDEX `index_time_history`(`time_history`) USING BTREE");  
		tablesql.append(" ) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;");  
		TABLE_SQL = tablesql;
	}
	
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void createTableEveryday() throws SQLException {
		Date today = new Date();
		SimpleDateFormat str = new SimpleDateFormat(REGRX);
		StringBuffer tableName = new StringBuffer();
		//根据日期生成表名
		tableName.append(PREFIX).append(str.format(today));
		ResultSet tabs = null;
		Connection conn = jdbcTemplate.getDataSource().getConnection();
		DatabaseMetaData dbMetaData = conn.getMetaData();
		String[] types = { "TABLE" };
		tabs = dbMetaData.getTables(null, null, tableName.toString(), types);
		//如果没有这表  则创建表
		if (!tabs.next()) {  
			StringBuffer sql = new StringBuffer("");  
			sql.append("CREATE TABLE `" + tableName + "` (");  
			sql.append(TABLE_SQL);
			jdbcTemplate.update(sql.toString());  
        }  

	}
}

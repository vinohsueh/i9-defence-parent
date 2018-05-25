package i9.defence.platform.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* 创建时间：2018年5月22日 上午11:10:36
* @author  lby
* @version  
* 
*/
public class SqlUtil {
	
	public static final String DEVICE_PREFIX = "device_record";
	
	
	public static final String REGRX = "yyyyMMdd";
	
	public static StringBuffer TABLE_SQL = null;
	
	public static final String ERROR_TYPE = "报警";
	
	public static final String LOOP_NAME = "号回路";
	
	public static final String CHANNEL_NAME = "号通道";
	
	public static final String NORMAL_CODE = "00000000";
	
	public static final String EQUIPMENT_INFO_SQL  = "select id,equipmentRemarks,equipmentCategoryId from t_equipment where deviceId = ?";
	
	public static final String EQUIPMENT_ERROR_SQL =  "select name,code,equipmentId from t_equipment_fault";
	
	public static final String UPDATE_EQUIPMENT_SQL = "update device_list set status = ? where device_code = ?";
	
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
	
	/**
	 * 获取表名
	 * @return
	 */
	public static StringBuffer getDeviceLogTableName(){
		Date today = new Date();
		SimpleDateFormat str = new SimpleDateFormat(REGRX);
		StringBuffer tableName = new StringBuffer();
		//根据日期生成表名
		return tableName.append(DEVICE_PREFIX).append(str.format(today));
	}
	
	/**
	 * 获取插入语句
	 * @return
	 */
	public static StringBuffer getInsertDeviceSql(){
		StringBuffer stringBuffer = new StringBuffer("insert into ");
		stringBuffer.append(getDeviceLogTableName());
		stringBuffer.append("(`device_code`, `time_history`, `create_time`, `record1`, `record2`, `record3`) values(?,?,?,?,?,?)");
		return stringBuffer;
	}
}

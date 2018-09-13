package i9.defence.platform.service.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


/**
 * @ClassName: DeviceRowMapper 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月30日 上午9:35:57
 */
public class DeviceRowMapper implements RowMapper<DeviceInfoDto>{

	@Override
	public DeviceInfoDto mapRow(ResultSet resultSet, int i) throws SQLException {
		DeviceInfoDto device = new DeviceInfoDto();
        device.setId(resultSet.getInt("id"));
        device.setRemarks(resultSet.getString("equipmentRemarks"));
        device.setEquipmentId(resultSet.getInt("equipmentCategoryId"));
        device.setProjectId(resultSet.getInt("projectId"));
        return device;
	}

}

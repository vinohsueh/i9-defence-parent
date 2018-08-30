package i9.defence.platform.service.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


/**
 * @ClassName: DeviceErrorRowMapper 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月30日 上午9:38:08
 */
public class DeviceErrorRowMapper implements RowMapper<DeviceError>{

	@Override
	public DeviceError mapRow(ResultSet resultSet, int i) throws SQLException {
    	DeviceError error = new DeviceError();
    	error.setName(resultSet.getString("name"));
    	error.setCode(resultSet.getString("code"));
    	error.setCatagoryId(resultSet.getInt("equipmentId"));
        return error;
    }

}

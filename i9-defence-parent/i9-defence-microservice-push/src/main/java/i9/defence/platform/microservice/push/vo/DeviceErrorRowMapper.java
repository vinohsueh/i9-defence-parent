package i9.defence.platform.microservice.push.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/** 
* 创建时间：2018年5月22日 下午3:04:50
* @author  lby
* @version  
* 
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
